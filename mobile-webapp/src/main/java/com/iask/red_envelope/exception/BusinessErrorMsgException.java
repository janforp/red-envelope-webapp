package com.iask.red_envelope.exception;

/**
 * Created by wuqiang on 15-8-7.
 * <p/>
 * 业务错误消息异常，当抛出此异常时，会把此异常的message返回给客户端
 *
 * @author wuqiang
 */
public class BusinessErrorMsgException extends RuntimeException {
    // message属性文件中的错误代码
    private Integer code;
    private String message;

    /**
     * 直接提供错误代码，Interceptor会自动处理
     *
     * @param code
     */
    public BusinessErrorMsgException(Integer code) {
        this(code, null);
    }

    public BusinessErrorMsgException(Integer code, String message) {
        super(message != null ? message : (code != null ? code.toString() : ""));
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
