package kr.co.opennote.booster.enums;

public enum ResponseType {
    SUCCESS(200, "Success"),
    INVALID(401,"Include invalid values"),
    UNAUTHORIZED(402, "Unauthorized"),
    INVALID_FILETYPE(403, "include invalid file types"),
    EXCEED_FILESIZE(403, "exceed file size"),
    NOT_SUPPORTED_FILE(415, "not supported file"),
    INTERNAL_SERVER_ERROR(500, "Occur Internal Server Error"),
    NONE(400, "Bad Request"),
    ;

    ResponseType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
