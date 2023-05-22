package kr.co.opennote.booster.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("voting")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Voting {
    private String uuid;
    private String itemUuid;
    private String regrId;
    private String regDt;
    private String useYn;
}
