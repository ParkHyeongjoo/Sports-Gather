package kr.co.opennote.booster.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("voteItem")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteItem {
    private String uuid;
    private String boardUuid;
    private String cont;
    private int cnt;
    private int order;
    private String percent;
}
