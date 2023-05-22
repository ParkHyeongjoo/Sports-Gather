package kr.co.opennote.booster.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteItemDto {
    private String uuid;
    private String boardUuid;
    private String cont;
    private int cnt;
    private int order;
    private String percent;
}
