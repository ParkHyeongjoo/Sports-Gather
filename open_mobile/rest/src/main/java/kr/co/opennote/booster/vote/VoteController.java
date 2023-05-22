package kr.co.opennote.booster.vote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.opennote.booster.domain.Vote;
import kr.co.opennote.booster.dto.ResponseDto;
import kr.co.opennote.booster.dto.VoteDto;
import kr.co.opennote.booster.dto.VoteItemDto;
import kr.co.opennote.booster.enums.ResponseType;
import kr.co.opennote.booster.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "투표", description = "투표 관련 API")
@RequestMapping("${api.version}/vote")
@RequiredArgsConstructor
@RestController
@Slf4j
public class VoteController {

    private final VoteService voteService;

    @Operation(summary = "목록 조회", description = "투표 목록 조회 API")
    @GetMapping("/getList.json")
    public ResponseEntity<ResponseDto<?>> list() {
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), voteService.getList()));
    }

    @Operation(summary = "게시글 상세 조회", description = "투표 게시글 상세 조회 API")
    @GetMapping("/get.json")
    public ResponseEntity<ResponseDto<?>> get(@RequestParam String uuid) {
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), voteService.get(uuid)));
    }

    @Operation(summary = "투표 참여 여부", description = "투표 참여 여부 조회 API")
    @GetMapping("/checkVoting.json")
    public ResponseEntity<ResponseDto<Boolean>> checkVoting(@RequestHeader("id") String regrId, @RequestParam String uuid){
        ResponseType resType = ResponseType.SUCCESS;
        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), voteService.checkVoting(uuid, regrId)));
    }

    @Operation(summary = "게시글 등록", description = "투표 게시글 등록 API")
    @PostMapping("/postProc.json")
    public ResponseEntity<ResponseDto<?>> post(@RequestBody VoteDto dto) {
        ResponseType resType = ResponseType.SUCCESS;
        try {
            voteService.post(dto);
        }catch (Exception e){
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            log.error(e.getMessage());
        }

        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), null));
    }


    @Operation(summary = "투표하기", description = "투표하기 API")
    @PostMapping("/votingProc.json")
    public ResponseEntity<ResponseDto<?>> voting(@RequestBody VoteItemDto dto, @RequestHeader(value = "id") String id){
        ResponseType resType = ResponseType.SUCCESS;

        try {
            voteService.voting(dto, id);
        }catch (Exception e){
            resType = ResponseType.INTERNAL_SERVER_ERROR;
            log.error(e.getMessage());
        }

        return ResponseEntity.ok(new ResponseDto<>(resType.getCode(), resType.getMessage(), null));
    }
}
