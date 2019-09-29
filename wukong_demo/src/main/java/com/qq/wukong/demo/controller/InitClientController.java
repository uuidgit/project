package com.qq.wukong.demo.controller;

import com.qq.wukong.demo.exception.BizException;
import com.qq.wukong.demo.helper.InitClientHelper;
import com.qq.wukong.demo.utils.ResultVo;
import com.qq.wukong.demo.utils.ResultVoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 14:16
 * @Description: 1    客户端初始化
 */
@RequestMapping(value = "/client")
@RestController
public class InitClientController {
    private static final Logger logger = LoggerFactory.getLogger(InitClientController.class);

    /**
     * @description: 1.1 注册客户端
     * @auther: 天雪
     * @date: 2019/9/27 14:32
     */
    @PostMapping("/register")
    public ResultVo<String> registerClient() throws BizException {
        logger.info(" registerClient start...");
        String projectId = InitClientHelper.registerClient();
        logger.info(" registerClient end");
        return ResultVoUtils.success(projectId);
    }

    /**
     * @description: 1.2 获取客户端
     * @auther: 天雪
     * @date: 2019/9/27 14:32
     */
    @GetMapping("/{projectId}")
    public ResultVo getClient(@PathVariable("projectId") String projectId) throws BizException {
        logger.info(" getClient start...");
        InitClientHelper.getServiceClient(projectId);
        logger.info(" getClient end");
        return ResultVoUtils.success();
    }

    /**
     * @description: 1.3 关闭客户端
     * @auther: 天雪
     * @date: 2019/9/27 14:32
     */
    @DeleteMapping("/{projectId}")
    public ResultVo shutdownClient(@PathVariable("projectId") String projectId) throws BizException {
        logger.info(" shutdownClient start...");
        InitClientHelper.shutDownServiceClient(projectId);
        logger.info(" shutdownClient end");
        return ResultVoUtils.success();
    }
}
