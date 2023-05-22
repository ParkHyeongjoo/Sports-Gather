package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.Board;
import kr.co.opennote.booster.dto.BoardDto;
import org.mapstruct.Mapper;

@Mapper
public interface BoardMapper extends GenericMapper<BoardDto, Board> {
}
