package kr.co.opennote.booster.hashTag.repository;

import kr.co.opennote.booster.domain.HashTag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HashTagRepository {
    List<HashTag> getListByBoardUuid(String uuid);
    List<HashTag> getListByTagUuid(String uuid);
    HashTag getByTagNm(String tagNm);
    void postHashTag(HashTag dto);
    void postHashTagBoard(HashTag dto);
    void deleteTagByBoardUuid(String uuid);
    void deleteTagBoard(String uuid);
}
