package kr.co.opennote.booster.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Alias("vote")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    private String uuid;
    private String regrId;
    private String regDt;
    private String updrId;
    private String updDt;
    private String title;
    private String cont;
    private String strtDt;
    private String endDt;
    private String views;
    private String useYn;
    private String status;
    private List<VoteItem> items;
}
