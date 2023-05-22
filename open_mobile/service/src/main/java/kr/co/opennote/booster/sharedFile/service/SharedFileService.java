package kr.co.opennote.booster.sharedFile.service;

import io.micrometer.common.util.StringUtils;
import kr.co.opennote.booster.attachment.service.AttachmentService;
import kr.co.opennote.booster.domain.SharedFile;
import kr.co.opennote.booster.dto.SharedFileDto;
import kr.co.opennote.booster.mapper.SharedFileMapper;
import kr.co.opennote.booster.sharedFile.repository.SharedFileRepository;
import kr.co.opennote.booster.hashTag.service.HashTagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SharedFileService {

    private final SharedFileRepository sharedFileRepository;
    private final AttachmentService attachmentService;
    private final HashTagService hashTagService;

    private final SharedFileMapper sharedFileMapper;


    public List<SharedFileDto> getList(String keyword) {
        List<SharedFile> sharedFiles = sharedFileRepository.getList(keyword);
        List<SharedFileDto> resultList = sharedFileMapper.toDto(sharedFiles);

        for(SharedFileDto dto : resultList){
            // 첨부파일
            if(StringUtils.isNotEmpty(dto.getAtflId())){
                dto.setFileList(attachmentService.getList(dto.getAtflId()));
            }
            // 해쉬태그
            dto.setTagList(hashTagService.getListByBoardUuid(dto.getUuid()));
        }

        return resultList;
    }

    public SharedFileDto get(String uuid) {
        SharedFile sharedFile = sharedFileRepository.get(uuid);
        SharedFileDto dto = sharedFileMapper.toDto(sharedFile);
        if(dto != null) {
            // 첨부파일
            if(StringUtils.isNotEmpty(dto.getAtflId())){
                dto.setFileList(attachmentService.getList(dto.getAtflId()));
            }
            // 해쉬태그
            dto.setTagList(hashTagService.getListByBoardUuid(dto.getUuid()));
        }
        return dto;
    }

    public void post(SharedFileDto dto){
        // UUID
        dto.setUuid(String.valueOf(UUID.randomUUID()));

        if(dto.getTagList() != null){
            hashTagService.post(dto.getTagList(), dto.getUuid());
        }

        SharedFile sharedFile = sharedFileMapper.toEntity(dto);
        sharedFileRepository.post(sharedFile);
    }

    public void patch(SharedFileDto dto) {
        if(StringUtils.isNotEmpty(dto.getUuid())){
            // 기존 해쉬태그 USE_YN = 'N'
            hashTagService.deleteByBoardUuid(dto.getUuid());

            // 첨부파일 변경되었으면 기존 첨부파일 제거
            SharedFileDto sharedFileDto = this.get(dto.getUuid());
            if(!dto.getAtflId().equals(sharedFileDto.getAtflId())){
                attachmentService.delete(dto.getFileList());
            }
        }

        if(dto.getTagList() != null){
            hashTagService.post(dto.getTagList(), dto.getUuid());
        }

        SharedFile sharedFile = sharedFileMapper.toEntity(dto);
        sharedFileRepository.patch(sharedFile);

    }

    public void delete(String uuid) {
        // 기존 해쉬태그 USE_YN = 'N'
        if(StringUtils.isNotEmpty(uuid)){
            hashTagService.deleteByBoardUuid(uuid);
        }

        sharedFileRepository.delete(uuid);
    }
}
