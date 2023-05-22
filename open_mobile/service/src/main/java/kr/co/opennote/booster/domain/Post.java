package kr.co.opennote.booster.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Alias("post")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private String uuid;
    private String regrId;
    private String regDt;
    private String updrId;
    private String updDt;
    private String category;
    private String title;
    private String content;
    private int views;
    private String atflId;
    private String useYn;
}
