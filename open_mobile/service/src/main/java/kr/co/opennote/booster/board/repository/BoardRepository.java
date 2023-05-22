package kr.co.opennote.booster.board.repository;

import kr.co.opennote.booster.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<Board> getList();

    void register(Board board);
}
