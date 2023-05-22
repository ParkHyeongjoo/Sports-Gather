package kr.co.opennote.booster.enums;

public enum FileType {
    /*
    확장자 : metadata.get(Metadata.CONTENT_TYPE)
    xlsx: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
    pptx: application/vnd.openxmlformats-officedocument.presentationml.presentation
    docx: application/vnd.openxmlformats-officedocument.wordprocessingml.document
    png: image/png
    txt: text/plain
    html: text/html
    hwp: application/x-tika-msoffice
    zip: application/zip
    apk: application/vnd.android.package-archive
    jpg, jpeg: image/jpeg

    -- 참고 (확장자가 변경되었을 경우 tika.detect() 유형으로 받아짐)
    확장자: file.getContentType(), tika.detect()
    xlsx: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/x-tika-ooxml
    pptx: application/vnd.openxmlformats-officedocument.presentationml.presentation, application/x-tika-ooxml
    png: image/png, image/png
    txt: text/plain, text/plain
    html: text/html, text/plain
    hwp: application/haansofthwp, application/x-tika-msoffice
    zip: application/x-zip-compressed, application/zip
    jpg, jpeg: image/jpeg, image/jpeg
    */

    IMAGE(new String[]{"image/(.*)"}),
    DOC(new String[]{"application/vnd.openxmlformats-officedocument.(.*)","application/x-tika-msoffice","application/pdf", "text/plain"}),
    ZIP(new String[]{"application/x-zip-compressed", "application/zip"});

    private final String[] types;

    FileType(String[] types) {
        this.types = types;
    }

    public String[] getTypes() {
        return types;
    }
}
