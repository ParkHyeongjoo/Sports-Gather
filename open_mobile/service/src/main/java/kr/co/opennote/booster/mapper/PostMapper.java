package kr.co.opennote.booster.mapper;

import kr.co.opennote.booster.domain.Post;
import kr.co.opennote.booster.dto.PostDto;
import org.mapstruct.Mapper;


@Mapper
public interface PostMapper extends GenericMapper<PostDto, Post> {
}
