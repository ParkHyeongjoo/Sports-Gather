package kr.co.opennote.booster.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.opennote.booster.attachment.service.AttachmentService;
import kr.co.opennote.booster.dto.AttachmentDto;
import kr.co.opennote.booster.dto.ResponseDto;
import kr.co.opennote.booster.dto.PostDto;
import kr.co.opennote.booster.enums.FileType;
import kr.co.opennote.booster.enums.ResponseType;
import kr.co.opennote.booster.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "게시글", description = "게시판 게시글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {
    private final PostService postService;
    private final AttachmentService attachmentService;

    @Operation(summary = "게시글 목록 조회", description = "게시판 게시글 목록 조회 API")
    @Parameter(name = "divn", description = "게시글 CATEGORY", required = true)
    @GetMapping("/{divn}/getList.json")
    public ResponseEntity<ResponseDto<List<PostDto>>> getList(@PathVariable String divn) {
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), postService.getList(divn)));
    }

    @Operation(summary = "게시글 검색", description = "게시판 게시글 검색 API")
    @Parameter(name = "keyword", description = "검색어", required = false)
    @Parameter(name = "divn", description = "게시글 CATEGORY", required = true)
    @GetMapping("/getRequestList.json")
    public ResponseEntity<ResponseDto<List<PostDto>>> getRequestList(String keyword, String divn) {
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), postService.getRequestList(keyword, divn)));
    }

    @Operation(summary = "게시글 상세 조회", description = "게시판 게시글 상세 조회 API")
    @Parameter(name = "uuid", description = "게시글 UUID", required = true)
    @GetMapping("/{uuid}/get.json")
    public ResponseEntity<ResponseDto<PostDto>> get(@PathVariable String uuid) {
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), postService.get(uuid)));
    }

    @Operation(summary = "게시글 등록", description = "게시판 게시글 등록 API")
    @Parameter(schema = @Schema(implementation = PostDto.class))
    @PostMapping("/registerProc.json")
    public ResponseEntity<ResponseDto<?>> register(@RequestBody() PostDto dto) {
        ResponseType resType = ResponseType.SUCCESS;
        try {
            postService.register(dto);
        }catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), dto.getUuid()));
    }

    @PostMapping("/increaseViews.json")
    public ResponseEntity<ResponseDto<?>> increaseViews(@RequestBody() String uuid) {
        ResponseType resType = ResponseType.SUCCESS;
        try {
            postService.increaseViews(uuid);
        }catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), null));
    }

    @Operation(summary = "게시글 수정", description = "게시판 게시글 수정 API")
    @Parameter(schema = @Schema(implementation = PostDto.class))
    @PostMapping("/patchProc.json")
    public ResponseEntity<ResponseDto<?>> patch(@RequestBody() PostDto dto) {
        ResponseType resType = ResponseType.SUCCESS;
        try {
            postService.patch(dto);
        }catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), dto.getUuid()));
    }

    @Operation(summary = "게시글 삭제", description = "게시판 게시글 삭제 API")
    @Parameter(name = "uuid", description = "게시글 UUID", required = true)
    @PostMapping("/deleteProc.json")
    public ResponseEntity<ResponseDto<?>> delete(@RequestBody() String uuid) {
        ResponseType resType = ResponseType.SUCCESS;
        try {
            postService.delete(uuid);
        }catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), null));
    }

    @Operation(summary = "파일 업로드", description = "게시판 파일 업로드 API")
    @PostMapping(value = "/file/upload.json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDto<?>> post(@RequestPart("files") List<MultipartFile> files) throws Exception{
        FileType[] allowed = new FileType[]{FileType.DOC, FileType.IMAGE, FileType.ZIP};
        // TODO regrId
        AttachmentDto dto = AttachmentDto.builder().regrId("TEST").build();
        return ResponseEntity.ok(attachmentService.post(allowed, files, dto));
    }
}
