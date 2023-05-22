package kr.co.opennote.booster.vote.service;

import kr.co.opennote.booster.domain.VoteItem;
import kr.co.opennote.booster.dto.VoteDto;
import kr.co.opennote.booster.dto.VoteItemDto;
import kr.co.opennote.booster.dto.VotingDto;
import kr.co.opennote.booster.mapper.VoteItemMapper;
import kr.co.opennote.booster.mapper.VoteMapper;
import kr.co.opennote.booster.mapper.VotingMapper;
import kr.co.opennote.booster.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final VoteItemMapper voteItemMapper;
    private final VotingMapper votingMapper;

    public List<VoteDto> getList() {
        return voteMapper.toDto(voteRepository.getList());
    }

    public VoteDto get(String uuid) {
        VoteDto dto = voteMapper.toDto(voteRepository.get(uuid));
        // 조회수 증가
        voteRepository.patchViews(voteMapper.toEntity(dto));
        // 투표 항목
        List<VoteItem> items = voteRepository.getVoteItems(uuid);
        dto.setItems(voteItemMapper.toDto(items));
        return dto;
    }

    public void post(VoteDto dto) {
        // UUID
        dto.setUuid(String.valueOf(UUID.randomUUID()));
        voteRepository.post(voteMapper.toEntity(dto));
        int order = 0;
        for(VoteItemDto itemDto : dto.getItems()){
            itemDto.setBoardUuid(dto.getUuid());
            itemDto.setOrder(order++);
            itemDto.setUuid(String.valueOf(UUID.randomUUID()));
            voteRepository.postItem(voteItemMapper.toEntity(itemDto));
        }
    }

    public void voting(VoteItemDto dto, String userId) {
        // cnt +1
        voteRepository.patchItemCnt(voteItemMapper.toEntity(dto));
        VotingDto votingDto = VotingDto.builder()
                .uuid(String.valueOf(UUID.randomUUID()))
                .itemUuid(dto.getUuid())
                .regrId(userId)
                .build();
        voteRepository.postVoting(votingMapper.toEntity(votingDto));
    }

    public Boolean checkVoting(String uuid, String regrId) {
        return voteRepository.checkVoting(uuid, regrId);
    }
}
