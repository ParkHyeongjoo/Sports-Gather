package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.Vote;
import kr.co.opennote.booster.dto.VoteDto;
import org.mapstruct.Mapper;

@Mapper
public interface VoteMapper extends GenericMapper<VoteDto, Vote>{
}
