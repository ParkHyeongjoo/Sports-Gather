package kr.co.opennote.booster.domain;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("sharedFile")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharedFile {
    private String uuid;
    private String regrId;
    private String regDt;
    private String updrId;
    private String updDt;
    private String title;
    private String atflId;
    private String useYn;
}
