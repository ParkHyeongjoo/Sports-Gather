package kr.co.opennote.booster.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashTagDto {
    @Schema(description = "tag_uuid", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String tagUuid;
    @Schema(description = "board_uuid", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String boardUuid;
    @Schema(description = "uuid", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String uuid;
    @Schema(description = "태그명", example = "#tag")
    private String tagNm;
    @Schema(description = "사용여부", defaultValue = "Y", example = "Y")
    private String useYn;
}
