package kr.co.opennote.booster.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDto {
    @Schema(description = "최초등록자ID", example = "USER")
    private String regrId;
    @Schema(description = "최초등록일", example = "2023-01-01")
    private String regDt;
    @Schema(description = "최종변경자ID", example = "USER")
    private String updrId;
    @Schema(description = "최종변경일", example = "2023-01-01")
    private String updDt;
    @Schema(description = "물리파일경로명", example = "C:\\workspace\\upload\\booster2023\\01\\01\\12\\")
    private String phclFilePathNm;
    @Schema(description = "물리파일명", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String phclFileNm;
    @Schema(description = "실제파일명", example = "IMG_20221107_141421.jpg")
    private String fileRlNm;
    @Schema(description = "파일확장자명", example = "jpg")
    private String fileExtNm;
    @Schema(description = "파일용량", example = "10000")
    private String fileSizeVal;
    @Schema(description = "파일타입명", example = "image/jpeg")
    private String fileTpNm;
    @Schema(description = "첨부파일ID", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String atflId;
    @Schema(description = "파일순번", example = "1")
    private String fileSeqNo;
    @Schema(description = "썸네일파일명", example = "e79ea42a-aa52-4274-85d6-f0694e03137c")
    private String thmbFileNm;
    @Schema(description = "사용여부", defaultValue = "Y", example = "Y")
    private String useYn;
}
