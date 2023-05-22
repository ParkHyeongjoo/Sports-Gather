package kr.co.opennote.booster.domain;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Alias("attachment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    private String regrId;
    private String regDt;
    private String updrId;
    private String updDt;
    private String phclFilePathNm;
    private String phclFileNm;
    private String fileRlNm;
    private String fileExtNm;
    private String fileSizeVal;
    private String fileTpNm;
    private String atflId;
    private String fileSeqNo;
    private String thmbFileNm;
    private String useYn;
}
