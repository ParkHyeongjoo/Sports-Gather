package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.SharedFile;
import kr.co.opennote.booster.dto.SharedFileDto;
import org.mapstruct.Mapper;

@Mapper
public interface SharedFileMapper extends GenericMapper<SharedFileDto, SharedFile> {
}
