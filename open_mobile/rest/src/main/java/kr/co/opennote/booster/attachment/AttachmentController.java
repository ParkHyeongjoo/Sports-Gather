package kr.co.opennote.booster.attachment;

import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.opennote.booster.attachment.service.AttachmentService;
import kr.co.opennote.booster.dto.AttachmentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.nio.file.*;

@Tag(name = "첨부파일", description = "첨부파일 관련 API")
@RequestMapping("${api.version}/attachment")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AttachmentController {
    private final AttachmentService attachmentService;

    @Operation(summary = "파일", description = "파일 Resource")
    @GetMapping("/getFile.json")
    public ResponseEntity<Resource> getFile(@RequestParam String atflId,
                                                         @RequestParam String fileSeqNo,
                                                         @RequestParam String phclFileNm) throws Exception {

        AttachmentDto dto = AttachmentDto.builder()
                .atflId(atflId)
                .fileSeqNo(fileSeqNo)
                .phclFileNm(phclFileNm)
                .build();

        dto = attachmentService.get(dto);

        if(dto == null) {
            throw new FileNotFoundException("atflId: " + atflId + ", fileSeqNo: " + fileSeqNo + ", phclFileNm: " + phclFileNm);
        }

        // 썸네일 파일 있으면
        String file = dto.getPhclFilePathNm() + (StringUtils.isNotEmpty(dto.getThmbFileNm()) ? dto.getThmbFileNm() : dto.getPhclFileNm());
        Path path = Paths.get(file);
        Resource resource;
        try {
            // 실제 파일이 있는지
            path.toRealPath(LinkOption.NOFOLLOW_LINKS);
        }catch (NoSuchFileException e){
            log.error("File not Exist ::: atflId :{}, fileSeqNo :{}, phclFileNm :{}", atflId, fileSeqNo, phclFileNm);
            return ResponseEntity.noContent().build();
        }

        resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
