package com.exam.vo;

public class UploadResponse {
    private String fileName;
    private String originalFileName;
    private Long size;
    private String type;
    private String url;
    private Integer status;
    private String msg;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UploadResponse(String fileName, String originalFileName, String type, String url, Integer status ){
        this.fileName=fileName;
        this.originalFileName =originalFileName;
        this.type=type;
        this.url=url;
        this.status=status;
    }

    public UploadResponse(String originalFileName, Integer status , String msg){
        this.originalFileName =originalFileName;
        this.status=status;
        this.msg=msg;
    }

    public class Error {
        public static final String None = "None";
        public static final String OVERSIZE = "File Size largger than the maximum";
        public static final String ILLEGALEXTENSION = "Unsupported file type";
        public static final String FILENOTFOUND = "FileNotFound";
    }
}
