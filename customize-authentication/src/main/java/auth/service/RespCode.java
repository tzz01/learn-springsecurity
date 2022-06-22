package auth.service;

public enum RespCode {
    WX_CODE_IS_NULL(4001, "微信code为空"),
    TEST_NN(4002, "22222");
    private Integer statusCode;
    private String message;

    RespCode() {
    }

    RespCode(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) {
        System.out.println(RespCode.WX_CODE_IS_NULL.getStatusCode());
        System.out.println(RespCode.WX_CODE_IS_NULL.getMessage());

        System.out.println(RespCode.TEST_NN.getStatusCode());
        System.out.println(RespCode.TEST_NN.getMessage());
    }
}
