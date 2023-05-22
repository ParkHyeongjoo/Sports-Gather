package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.Attachment;
import kr.co.opennote.booster.dto.AttachmentDto;
import org.mapstruct.Mapper;

@Mapper
public interface AttachmentMapper extends GenericMapper<AttachmentDto, Attachment> {
}
