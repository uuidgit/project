package com.qq.wukong.demo.init;

import com.qq.wukong.demo.helper.InitClientHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 10:04
 * @Description:
 */
@Component
public class InitClientResources implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        logger.info("Init client resources.");
        String projectId = InitClientHelper.registerClient();
        InitClientHelper.getServiceClient(projectId);
    }
}
