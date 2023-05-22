package kr.co.opennote.booster.attachment.service;

import io.micrometer.common.util.StringUtils;
import kr.co.opennote.booster.attachment.repository.AttachmentRepository;
import kr.co.opennote.booster.domain.Attachment;
import kr.co.opennote.booster.dto.AttachmentDto;
import kr.co.opennote.booster.dto.ResponseDto;
import kr.co.opennote.booster.enums.FileType;
import kr.co.opennote.booster.enums.ResponseType;
import kr.co.opennote.booster.mapper.AttachmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentService {

    @Value("${file.upload.path}")
    private String UPLOAD_PATH;
    private final AttachmentRepository attachmentRepository;
    private  final AttachmentMapper attachmentMapper;

    public List<AttachmentDto> getList(String atflId){
        List<Attachment> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(atflId)){
            list = attachmentRepository.getList(atflId);
        }
        return attachmentMapper.toDto(list);
    }

    public AttachmentDto get(AttachmentDto attachmentDto){
        Attachment attachment = attachmentRepository.get(attachmentMapper.toEntity(attachmentDto));
        return attachmentMapper.toDto(attachment);
    }

    public ResponseDto<String> post(FileType[] fileTypes, List<MultipartFile> files, AttachmentDto attachmentDto) throws Exception {
        Tika tika = new Tika();
        String fileTpNm;
        ResponseType resType;

        log.info("=====================");
        for (MultipartFile file : files) {
            log.info("file atflId: " + attachmentDto.getAtflId());
            log.info("file contentType: " + file.getContentType());

            // metadata content-type 추출
            AutoDetectParser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            if(StringUtils.isNotEmpty(file.getOriginalFilename())){
                metadata.add(TikaCoreProperties.RESOURCE_NAME_KEY, URLDecoder.decode(file.getOriginalFilename(), StandardCharsets.UTF_8));
            }
            try (InputStream inputStream = TikaInputStream.get(file.getBytes(), metadata)) {
                parser.parse(inputStream, handler, metadata);
                log.info("file tika contentType: " + metadata.get(Metadata.CONTENT_TYPE));
                fileTpNm = metadata.get(Metadata.CONTENT_TYPE);
            } catch (IOException e) {
                log.info(e.getMessage());
                fileTpNm = tika.detect(file.getBytes());
            }

            log.info("file name: " + file.getName());
            if (StringUtils.isNotEmpty(file.getOriginalFilename())) {
                log.info("file originalFilename: " + URLDecoder.decode(file.getOriginalFilename(), StandardCharsets.UTF_8));
            }
            log.info("file size: " + file.getSize());

            // 유효성
            if (file.getSize() > 5242880) {
                // 파일 허용 용량 초과
                resType = ResponseType.EXCEED_FILESIZE;
                return new ResponseDto<>(resType.getCode(), resType.getMessage(), null);
            }

            // 파일 type 체크
            boolean flag = false;
            String temp = fileTpNm;
            for(FileType type : fileTypes){
                if (Arrays.stream(type.getTypes()).anyMatch(temp::matches)) flag = true;
            }

            if(!flag){
                // 업로드 불가능한 파일이 포함되어있는 경우
                resType = ResponseType.INVALID_FILETYPE;
                return new ResponseDto<>(resType.getCode(), resType.getMessage(), null);
            }
        }

        log.info("=====================");

        resType = ResponseType.SUCCESS;
        String atflId = StringUtils.isEmpty(attachmentDto.getAtflId()) ? "" : attachmentDto.getAtflId();
        Date date = new Date();

        // upload path.
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-");

        // Matcher.quoteReplacement 추가 (windows File.separator 에러).
        String uploadPath = UPLOAD_PATH + format.format(date).replaceAll("-", Matcher.quoteReplacement(File.separator));

        AttachmentDto dto;
        int idx = 0;
        File one;

        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                AutoDetectParser parser = new AutoDetectParser();
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();
                if(StringUtils.isNotEmpty(file.getOriginalFilename())){
                    metadata.add(TikaCoreProperties.RESOURCE_NAME_KEY, URLDecoder.decode(file.getOriginalFilename(), StandardCharsets.UTF_8));
                }
                try (InputStream inputStream = TikaInputStream.get(file.getBytes(), metadata)) {
                    parser.parse(inputStream, handler, metadata);
                    fileTpNm = metadata.get(Metadata.CONTENT_TYPE);
                } catch (IOException e) {
                    log.info(e.getMessage());
                    fileTpNm = tika.detect(file.getBytes());
                }

                if (idx == 0 && StringUtils.isEmpty(atflId)) {
                    atflId = String.valueOf(UUID.randomUUID());
                }

                String originalFileName = "";
                String fileExtNm = "";

                if (StringUtils.isNotEmpty(file.getOriginalFilename())) {
                    originalFileName = URLDecoder.decode(file.getOriginalFilename(), StandardCharsets.UTF_8);
                    // IE 에서는 파일 경로가 포함되므로 처리해 줌
                    if (originalFileName != null) {
                        originalFileName = originalFileName.substring(originalFileName.lastIndexOf("\\") + 1);
                        fileExtNm = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                    }
                }

                dto = AttachmentDto.builder()
                        .atflId(atflId)
                        .fileExtNm(fileExtNm)
                        .fileRlNm(originalFileName)
                        .fileSizeVal(String.valueOf(file.getSize()))
                        .fileTpNm(fileTpNm)
                        .phclFileNm(String.valueOf(UUID.randomUUID()))
                        .phclFilePathNm(uploadPath)
                        .regrId(attachmentDto.getRegrId())
                        .build();

                // 폴더 생성.
                one = new File(dto.getPhclFilePathNm());

                if (!one.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    one.mkdirs();
                }

                // 파일 생성.
                one = new File(dto.getPhclFilePathNm() + dto.getPhclFileNm());
                file.transferTo(one);
                attachmentRepository.post(attachmentMapper.toEntity(dto));

                idx++;
            }
        }
        return new ResponseDto<>(resType.getCode(), resType.getMessage(), atflId);
    }

    public void delete(List<AttachmentDto> files) {
        AttachmentDto dto;
        File one;
        File thmb;

        for (AttachmentDto file : files) {
            Attachment attachment = attachmentMapper.toEntity(file);
            attachment = attachmentRepository.get(attachment);
            dto = attachmentMapper.toDto(attachment);

            if (attachment != null) {
                one = new File(dto.getPhclFilePathNm() + dto.getPhclFileNm());

                if (!one.delete()) {
                    // 사용중이라 delete 실패하면 deleteOnExit
                    one.deleteOnExit();
                }

                if (org.apache.commons.lang3.StringUtils.isNotEmpty(dto.getThmbFileNm())) {
                    // 썸네일 이미지 있으면 같이 삭제
                    thmb = new File(dto.getPhclFilePathNm() + dto.getThmbFileNm());

                    if (!thmb.delete()) {
                        // 사용중이라 delete 실패하면 deleteOnExit
                        thmb.deleteOnExit();
                    }
                }

                attachmentRepository.delete(attachment);
            }
        }
    }
}
