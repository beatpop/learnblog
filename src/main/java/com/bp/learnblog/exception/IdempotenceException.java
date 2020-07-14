package com.bp.learnblog.exception;

/**
 * 接口幂等性异常处理
 *
 * @author DH
 */
public class IdempotenceException extends RuntimeException {

    /**
     * Constructs a blank IdempotenceException
     */
    public IdempotenceException() {
        super();
    }

    /**
     * Constructs a new IdempotenceException with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message
     */
    public IdempotenceException(String message) {
        super(message);
    }

    /**
     * Constructs a new IdempotenceException with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param message
     * @param cause
     */
    public IdempotenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new IdempotenceException with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause
     */
    public IdempotenceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new IdempotenceException with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    protected IdempotenceException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
