package com.qq.wukong.demo.helper;

import com.qq.wukong.demo.config.SystemConfig;
import com.qq.wukong.demo.exception.BizException;
import com.timevale.esign.sdk.tech.bean.result.Result;
import com.timevale.esign.sdk.tech.v3.client.ServiceClient;
import com.timevale.esign.sdk.tech.v3.client.ServiceClientManager;
import com.timevale.tech.sdk.bean.HttpConnectionConfig;
import com.timevale.tech.sdk.bean.ProjectConfig;
import com.timevale.tech.sdk.bean.SignatureConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * @description: 1 客户端初始化
 * @auther: 天雪
 * @date: 2019/9/27 13:29
 */
public class InitClientHelper {
    private static final Logger logger = LoggerFactory.getLogger(InitClientHelper.class);
    // 客户端初始化后的客户端
    public static ServiceClient serviceClient = null;

    /**
     * @description: 1.1 注册客户端
     * @auther: 天雪
     * @date: 2019/9/27 14:04
     * @since JDK1.8
     */
    public static String registerClient() throws BizException {
        // 1. 进行项目配置，从开放平台获取
        ProjectConfig projectConfig = getProjectConfig();
        // 2. Http配置
        HttpConnectionConfig httpConfig = getHttpConfig();
        // 3. 签名配置
        SignatureConfig signatureConfig = getSignatureConfig();
        // 4. 注册客户端
        Result result = ServiceClientManager.registClient(projectConfig, httpConfig, signatureConfig);
        // 5. 判断注册客户端结果
        if (result.getErrCode() != 0) {
            String rstMsg = MessageFormat.format("注册[{0}]的客户端失败：errorCode={1}，msg={2}",
                    projectConfig.getProjectId(), result.getErrCode(), result.getMsg());
            logger.error(rstMsg);
            throw new BizException(rstMsg);
        }
        logger.info("注册[{}]的客户端成功", projectConfig.getProjectId());
        return projectConfig.getProjectId();
    }

    /**
     * @param projectId {@link String} 项目ID
     * @return {@link ServiceClient} 已初始化的客户端对象
     * @description: 1.2 获取客户端
     * @auther: 天雪
     * @date: 2019/9/27 14:08
     */
    public static ServiceClient getServiceClient(String projectId) throws BizException {
        serviceClient = ServiceClientManager.get(projectId);
        if (serviceClient == null) {
            throw new BizException(MessageFormat.format(
                    "ServiceClient为空，获取[{0}]的客户端失败，请重新注册客户端", projectId));
        }
        logger.info("获取[{}]的客户端成功", projectId);
        return serviceClient;
    }

    /**
     * @param projectId {@link String} 项目ID
     * @description: 1.3 关闭客户端
     * @auther: 天雪
     * @date: 2019/9/27 14:13
     * @since JDK1.8
     */
    public static void shutDownServiceClient(String projectId) throws BizException {
        try {
            ServiceClientManager.shutdown(projectId);
        } catch (Exception e) {
            throw new BizException(MessageFormat.format(
                    "[{0}]的客户端关闭异常,请检查[{0}]的客户端是否注册成功或已关闭", projectId));
        }

        ServiceClient serviceClient = ServiceClientManager.get(projectId);
        if (serviceClient != null) {
            throw new BizException(MessageFormat.format("关闭[{0}]的客户端失败，请检查原因", projectId));
        }
        logger.info("[{}]的客户端关闭成功", projectId);
    }

    /**
     * @return {@link ProjectConfig} 项目配置对象
     * @description: 客户端初始化项目配置 (项目配置通过启动时加载配置文件引入)
     * @auther: 天雪
     * @date: 2019/9/27 13:31
     * @since JDK1.8
     */
    private static ProjectConfig getProjectConfig() {
        ProjectConfig config = new ProjectConfig();
        config.setProjectId(SystemConfig.getProjectId());
        config.setProjectSecret(SystemConfig.getProjectSecret());
        config.setItsmApiUrl(SystemConfig.getApiUrl());
        return config;
    }

    /**
     * @return {@link HttpConnectionConfig}   Http配置对象
     * @description: Http配置，与服务端通讯的http配置
     * @auther: 天雪
     * @date: 2019/9/27 13:38
     * @since: JDK1.8
     */
    private static HttpConnectionConfig getHttpConfig() {
        // 具体Http配置信息 - 注意不能使用null
        return new HttpConnectionConfig();
    }

    /**
     * @return {@link SignatureConfig}  SignatureConfig签名配置对象
     * @description: SignatureConfig签名配置，与服务端通讯的签名安全策略配置	可空
     * @auther: 天雪
     * @date: 2019/9/27 13:41
     * @since: JDK1.8
     */
    private static SignatureConfig getSignatureConfig() {
        // 具体签名配置 - 注意不能使用null
        return new SignatureConfig();
    }
}
