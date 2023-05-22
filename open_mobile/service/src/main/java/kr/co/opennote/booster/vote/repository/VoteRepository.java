package kr.co.opennote.booster.vote.repository;

import kr.co.opennote.booster.domain.Vote;
import kr.co.opennote.booster.domain.VoteItem;
import kr.co.opennote.booster.domain.Voting;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VoteRepository {

    List<Vote> getList();
    List<VoteItem> getVoteItems(String uuid);
    Vote get(String uuid);
    void post(Vote vote);
    void postVoting(Voting voting);
    void patch(Vote vote);
    void patchItemCnt(VoteItem voteItem);
    void patchViews(Vote vote);
    void delete(String uuid);

    void postItem(VoteItem voteItem);

    Boolean checkVoting(String uuid, String regrId);
}
