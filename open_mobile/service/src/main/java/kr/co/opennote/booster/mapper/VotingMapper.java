package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.Voting;
import kr.co.opennote.booster.dto.VotingDto;
import org.mapstruct.Mapper;

@Mapper
public interface VotingMapper extends GenericMapper<VotingDto, Voting> {
}
