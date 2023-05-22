package kr.co.opennote.booster.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {

    private String uuid;
    private String regrId;
    private String regDt;
    private String updrId;
    private String updDt;
    private String category;
    private String useYn;
}
