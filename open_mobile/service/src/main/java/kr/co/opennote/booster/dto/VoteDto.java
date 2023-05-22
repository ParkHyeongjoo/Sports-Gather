package kr.co.opennote.booster.dto;

import kr.co.opennote.booster.domain.VoteItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {
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
    private String status;
    private List<VoteItemDto> items;
}
