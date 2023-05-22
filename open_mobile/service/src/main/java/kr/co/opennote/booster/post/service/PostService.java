package kr.co.opennote.booster.post.service;

import io.micrometer.common.util.StringUtils;
import kr.co.opennote.booster.attachment.service.AttachmentService;
import kr.co.opennote.booster.domain.Post;
import kr.co.opennote.booster.dto.PostDto;
import kr.co.opennote.booster.mapper.PostMapper;
import kr.co.opennote.booster.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PostService {

    private final PostRepository postRepostiroy;
    private final AttachmentService attachmentService;

    private final PostMapper postMapper;

    public List<PostDto> getList(String divn) {
        List<Post> posts = postRepostiroy.getList(divn);

        return postMapper.toDto(posts);
    }

    public List<PostDto> getRequestList(String keyword, String divn) {
        List<Post> posts = postRepostiroy.getRequestList(keyword, divn);

        return postMapper.toDto(posts);
    }

    public PostDto get(String uuid) {
        Post post = postRepostiroy.get(uuid);
        PostDto dto = postMapper.toDto(post);

        if(StringUtils.isNotEmpty(dto.getAtflId())) {
            dto.setFileList(attachmentService.getList(dto.getAtflId()));
        }

        return dto;
    }

    public void register(PostDto dto) {
        // UUID
        dto.setUuid(String.valueOf(UUID.randomUUID()));

        Post post = postMapper.toEntity(dto);
        postRepostiroy.register(post);
    }

    public void increaseViews(String uuid) {
        postRepostiroy.increaseViews(uuid);
    }

    public void patch(PostDto dto) throws Exception {
        Post post = postMapper.toEntity(dto);
        postRepostiroy.patch(post);
    }

    public void delete(String uuid) throws Exception {
        postRepostiroy.delete(uuid);
    }
}
