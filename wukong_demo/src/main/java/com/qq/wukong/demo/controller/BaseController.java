package com.qq.wukong.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 14:17
 * @Description: restful接口类公共信息 - 可用于获取请求头中必要信息
 */
public class BaseController {
    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private HttpServletRequest request;

    protected void startPrint(String name) {
        logger.info("{} start ....", name);
    }

    protected void endPrint(String name) {
        logger.info("{} end.", name);
    }
}
