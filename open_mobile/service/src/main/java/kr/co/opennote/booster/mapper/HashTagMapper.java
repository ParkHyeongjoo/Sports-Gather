package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.HashTag;
import kr.co.opennote.booster.dto.HashTagDto;
import org.mapstruct.Mapper;

@Mapper
public interface HashTagMapper extends GenericMapper<HashTagDto, HashTag> {
}
