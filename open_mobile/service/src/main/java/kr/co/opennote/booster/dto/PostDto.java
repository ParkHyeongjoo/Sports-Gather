package kr.co.opennote.booster.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto {
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
    private List<AttachmentDto> fileList;
}
