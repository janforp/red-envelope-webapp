package com.iask.red_envelope.exception;

/**
 * 如果发生这个异常，则表明，此线程处理已经超时（5秒），如果在20秒内处理完成，则会由别的线程直接拿到处理结果并返回
 *
 * @author wu-qiang
 */
public class NotReplyException extends RuntimeException {

    private static final long serialVersionUID = 107684571198495159L;

    public NotReplyException() {
    }

    public NotReplyException(String message) {
        super(message);
    }

    public NotReplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotReplyException(Throwable cause) {
        super(cause);
    }

    public NotReplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}