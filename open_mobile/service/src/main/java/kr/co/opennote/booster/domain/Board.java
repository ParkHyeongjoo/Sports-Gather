package kr.co.opennote.booster.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("board")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private String uuid;
    private String regrId;
    private String regDt;
    private String updrId;
    private String updDt;
    private String category;
    private String useYn;
}
