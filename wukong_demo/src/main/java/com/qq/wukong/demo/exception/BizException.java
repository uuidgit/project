package com.qq.wukong.demo.exception;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 13:57
 * @Description: 业务异常类
 */
public class BizException extends Exception {
    private static final long serialVersionUID = 4359180081622082791L;

    /**
     * Constructs a {@code BizException} with no detail message.
     */
    public BizException() {
        super();
    }

    /**
     * Constructs a {@code BizException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public BizException(String s) {
        super(s);
    }
}
