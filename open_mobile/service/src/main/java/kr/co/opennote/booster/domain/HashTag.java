package kr.co.opennote.booster.domain;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("hashTag")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashTag {
    private String tagUuid;
    private String boardUuid;
    private String uuid;
    private String tagNm;
    private String useYn;
}
