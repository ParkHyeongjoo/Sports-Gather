package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.VoteItem;
import kr.co.opennote.booster.dto.VoteItemDto;
import org.mapstruct.Mapper;

@Mapper
public interface VoteItemMapper extends GenericMapper<VoteItemDto, VoteItem>{
}
