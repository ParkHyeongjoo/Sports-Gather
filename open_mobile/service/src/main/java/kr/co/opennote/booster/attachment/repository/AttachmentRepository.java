package kr.co.opennote.booster.attachment.repository;

import kr.co.opennote.booster.domain.Attachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentRepository {
    List<Attachment> getList(String atflId);

    Attachment get(Attachment attachment);

    void post(Attachment attachment);

    void delete(Attachment file);
}
