package kr.co.opennote.booster.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VotingDto {
    private String uuid;
    private String itemUuid;
    private String regrId;
    private String regDt;
    private String useYn;
}
