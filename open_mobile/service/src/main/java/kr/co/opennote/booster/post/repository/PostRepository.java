package kr.co.opennote.booster.post.repository;

import kr.co.opennote.booster.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {

    List<Post> getList(String divn);
    List<Post> getRequestList(String keyword, String divn);

    Post get(String uuid);
    void register(Post post);
    void increaseViews(String uuid);
    void patch(Post post);
    void delete(String uuid);
}
