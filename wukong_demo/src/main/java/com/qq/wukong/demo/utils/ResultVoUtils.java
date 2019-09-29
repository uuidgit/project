package com.qq.wukong.demo.utils;

import org.springframework.util.StringUtils;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 18:54
 * @Description: 响应结果封装
 */
public class ResultVoUtils {

    /**
     * @param data {@link String} 响应成功数据
     * @description: 成功响应
     * @auther: 天雪
     * @date: 2019/9/27 18:57
     * @return: {@link ResultVo}
     * @return: {@link ResultVo}
     * @since: JDK1.8
     */
    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> result = new ResultVo<>();
        result.setData(data);
        result.setMessage("OK");
        result.setCode(200);
        return result;
    }

    /**
     * @description: 成功响应
     * @auther: 天雪
     * @date: 2019/9/27 18:57
     * @return: {@link ResultVo}
     * @since: JDK1.8
     */
    public static <T> ResultVo<T> success() {
        ResultVo<T> result = new ResultVo<>();
        result.setMessage("OK");
        result.setCode(200);
        return result;
    }

    /**
     * @param code    {@link Integer} 错误码
     * @param message {@link String} 错误信息
     * @description: 失败响应
     * @auther: 天雪
     * @date: 2019/9/27 18:57
     * @return: {@link ResultVo}
     * @since: JDK1.8
     */
    public static <T> ResultVo<T> failure(int code, String message) {
        ResultVo<T> result = new ResultVo<>();
        result.setMessage(StringUtils.isEmpty(message) ? "failure" : message);
        result.setCode(code == 0 ? code : code);
        return result;
    }

    /**
     * @param message {@link String} 错误信息
     * @description: 失败响应
     * @auther: 天雪
     * @date: 2019/9/27 18:57
     * @return: {@link ResultVo}
     * @since: JDK1.8
     */
    public static <T> ResultVo<T> failure(String message) {
        ResultVo<T> result = new ResultVo<>();
        result.setMessage(StringUtils.isEmpty(message) ? "failure" : message);
        result.setCode(500);
        return result;
    }

    /**
     * @description: 失败响应
     * @auther: 天雪
     * @date: 2019/9/27 18:57
     * @return: {@link ResultVo}
     * @since: JDK1.8
     */
    public static <T> ResultVo<T> failure() {
        ResultVo<T> result = new ResultVo<>();
        result.setMessage("failure");
        result.setCode(500);
        return result;
    }
}
