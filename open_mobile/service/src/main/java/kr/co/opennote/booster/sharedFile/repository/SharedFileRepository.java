package kr.co.opennote.booster.sharedFile.repository;

import kr.co.opennote.booster.domain.SharedFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SharedFileRepository {

    List<SharedFile> getList(String keyword);
    SharedFile get(String uuid);
    void post(SharedFile sharedFile);
    void patch(SharedFile sharedFile);
    void delete(String uuid);
}
