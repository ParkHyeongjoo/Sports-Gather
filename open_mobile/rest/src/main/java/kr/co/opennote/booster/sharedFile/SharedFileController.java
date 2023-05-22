package kr.co.opennote.booster.sharedFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.opennote.booster.attachment.service.AttachmentService;
import kr.co.opennote.booster.dto.AttachmentDto;
import kr.co.opennote.booster.dto.SharedFileDto;
import kr.co.opennote.booster.dto.ResponseDto;
import kr.co.opennote.booster.enums.FileType;
import kr.co.opennote.booster.enums.ResponseType;
import kr.co.opennote.booster.sharedFile.service.SharedFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "파일공유", description = "파일공유 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("${api.version}/sharedFile")
@Slf4j
public class SharedFileController {
    private final SharedFileService sharedFileService;
    private final AttachmentService attachmentService;

    @Operation(summary = "목록 조회", description = "파일공유 목록 조회 API")
    @GetMapping("/getList.json")
    public ResponseEntity<ResponseDto<List<SharedFileDto>>> getList(@RequestParam(required = false) String keyword){
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), sharedFileService.getList(keyword)));
    }

    @Operation(summary = "게시글 상세 조회", description = "파일공유 게시글 상세 조회 API")
    @Parameter(name = "uuid", description = "게시글 UUID", required = true)
    @GetMapping("/get.json")
    public ResponseEntity<ResponseDto<SharedFileDto>> get(String uuid) {
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), sharedFileService.get(uuid)));
    }

    @Operation(summary = "게시글 등록", description = "파일공유 게시글 등록 API")
    @Parameter(schema = @Schema(implementation = SharedFileDto.class))
    @PostMapping("/postProc.json")
    public ResponseEntity<ResponseDto<?>> post(@RequestBody() SharedFileDto dto) {
        ResponseType resType = ResponseType.SUCCESS;
        try {
            sharedFileService.post(dto);
        } catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), dto.getUuid()));
    }

    @Operation(summary = "게시글 수정", description = "파일공유 게시글 수정 API")
    @Parameter(schema = @Schema(implementation = SharedFileDto.class))
    @PatchMapping("/patchProc.json")
    public ResponseEntity<ResponseDto<?>> patch(@RequestBody SharedFileDto dto){
        ResponseType resType = ResponseType.SUCCESS;
        try {
            sharedFileService.patch(dto);
        } catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), dto.getUuid()));
    }

    @Operation(summary = "게시글 삭제", description = "파일공유 게시글 삭제 API")
    @Parameter(name = "uuid", description = "게시글 UUID", required = true)
    @DeleteMapping("/deleteProc.json")
    public ResponseEntity<ResponseDto<?>> deleteProc(String uuid){
        ResponseType resType = ResponseType.SUCCESS;
        try {
            sharedFileService.delete(uuid);
        } catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), null));
    }

    @Operation(summary = "파일 업로드", description = "파일공유 파일 업로드 API")
    @PostMapping(value = "/file/upload.json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<?>> post(@RequestPart("files") List<MultipartFile> files) throws Exception{
        FileType[] allowed = new FileType[]{FileType.DOC, FileType.IMAGE, FileType.ZIP};
        // TODO regrId
        AttachmentDto dto = AttachmentDto.builder().regrId("TEST").build();
        return ResponseEntity.ok(attachmentService.post(allowed, files, dto));
    }

}
