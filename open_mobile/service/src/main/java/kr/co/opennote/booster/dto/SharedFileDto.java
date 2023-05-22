package kr.co.opennote.booster.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SharedFileDto {
    @Schema(description = "uuid", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String uuid;
    @Schema(description = "최초등록자ID", example = "USER")
    private String regrId;
    @Schema(description = "최초등록일", example = "2023-01-01")
    private String regDt;
    @Schema(description = "최종변경자ID", example = "USER")
    private String updrId;
    @Schema(description = "최종변경일", example = "2023-01-01")
    private String updDt;
    @Schema(description = "제목", example = "제목입니다.")
    private String title;
    @Schema(description = "첨부파일ID", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String atflId;
    @Schema(description = "사용여부", defaultValue = "Y", example = "Y")
    private String useYn;
    private List<AttachmentDto> fileList;
    private List<HashTagDto> tagList;
}
