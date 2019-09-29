package com.qq.wukong.demo.exception;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 14:00
 * @Description: 业务运行时抛出异常
 */
public class BizRunException extends RuntimeException {
    private static final long serialVersionUID = 1162710183389028712L;

    /**
     * Constructs a {@code BizRunException} with no detail message.
     */
    public BizRunException() {
        super();
    }

    /**
     * Constructs a {@code BizRunException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public BizRunException(String s) {
        super(s);
    }
}
