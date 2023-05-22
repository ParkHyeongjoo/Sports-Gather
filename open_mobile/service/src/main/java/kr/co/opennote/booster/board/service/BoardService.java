package kr.co.opennote.booster.board.service;

import kr.co.opennote.booster.board.repository.BoardRepository;
import kr.co.opennote.booster.domain.Board;
import kr.co.opennote.booster.dto.BoardDto;
import kr.co.opennote.booster.mapper.BoardMapper;
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
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    public List<BoardDto> getList() {
        List<Board> boards = boardRepository.getList();

        return boardMapper.toDto(boards);
    }

    public void register(BoardDto dto) throws Exception {
        dto.setUuid(String.valueOf(UUID.randomUUID()));

        Board board = boardMapper.toEntity(dto);
        boardRepository.register(board);
    }
}
