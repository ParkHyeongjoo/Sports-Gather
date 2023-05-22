package kr.co.opennote.booster.board;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.opennote.booster.board.service.BoardService;
import kr.co.opennote.booster.dto.BoardDto;
import kr.co.opennote.booster.dto.ResponseDto;
import kr.co.opennote.booster.enums.ResponseType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "게시판", description = "게시판 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @Operation(summary = "게시판 목록 조회", description = "게시판 목록 조회 API")
    @GetMapping("/getList.json")
    public ResponseEntity<ResponseDto<List<BoardDto>>> getList() {
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), boardService.getList()));
    }

    @Operation(summary = "게시판 생성", description = "게시판 생성 API")
    @Parameter(schema = @Schema(implementation = BoardDto.class))
    @PostMapping("/registerProc.json")
    public ResponseEntity<ResponseDto<?>> register(@RequestBody() BoardDto dto) {
        System.out.println("controller : " + dto);
        ResponseType resType = ResponseType.SUCCESS;
        try {
            boardService.register(dto);
        }catch (Exception e) {
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            e.printStackTrace();
        }
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), dto.getUuid()));
    }
}
