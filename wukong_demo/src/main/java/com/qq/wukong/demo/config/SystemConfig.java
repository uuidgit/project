package com.qq.wukong.demo.config;

import com.timevale.tech.sdk.constants.AlgorithmType;
import com.timevale.tech.sdk.constants.HttpType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfig {
    // 项目ID(公共应用ID),正式环境下贵司将拥有独立的应用ID
    private static String PROJECT_ID = "4438765847";

    // 项目Secret(公共应用Secret),正式环境下贵司将拥有独立的应用Secret
    private static String PROJECT_SECRET = "512dd8bc05aaf5f8382f4d00f5e61e17";

    //协议类型（可选HTTP或HTTPS)
    private static HttpType HTTP_TYPE = HttpType.HTTP;

    //e签宝环境地址 模拟：http://smlitsm.tsign.cn:8080    正式http://openapi.tsign.cn:8080：
    private static String API_HOST = "http://smlitsm.tsign.cn:8080";

    //开放平台地址
    private static String API_URL = API_HOST + "/tgmonitor/rest/app!getAPIInfo2";

    //算法类型（可选HMACSHA256/RSA，推荐使用HMACSHA256)
    private static AlgorithmType ALGORITHM_TYPE = AlgorithmType.HMACSHA256;

    //e签宝公钥，可从开放平台获取，若算法类型为RSA，此项必填
    private static String ESIGN_PUB_KEY = null;

    //e签宝私钥，可从开放平台下载密钥生成工具生成，若算法为RSA，此项必填
    private static String ESIGN_PRI_KEY = null;

    //当前悟空SDK版本号
    private static String TECH_SDK_VERSION = "2.1.9";

    public static String getProjectId() {
        return PROJECT_ID;
    }

    @Value("${project.id}")
    public void setProjectId(String projectId) {
        PROJECT_ID = projectId;
    }

    public static String getProjectSecret() {
        return PROJECT_SECRET;
    }

    @Value("${project.secret}")
    public void setProjectSecret(String projectSecret) {
        PROJECT_SECRET = projectSecret;
    }

    public static HttpType getHttpType() {
        return HTTP_TYPE;
    }

    public static String getApiHost() {
        return API_HOST;
    }

    @Value("${api.host}")
    public void setApiHost(String apiHost) {
        API_HOST = apiHost;
    }

    public static String getApiUrl() {
        return API_URL;
    }

    @Value("${api.url}")
    public void setApiUrl(String apiUrl) {
        API_URL =API_HOST +  apiUrl;
    }

    public static AlgorithmType getAlgorithmType() {
        return ALGORITHM_TYPE;
    }


    public static String getEsignPubKey() {
        return ESIGN_PUB_KEY;
    }

    public static String getEsignPriKey() {
        return ESIGN_PRI_KEY;
    }


    public static String getTechSdkVersion() {
        return TECH_SDK_VERSION;
    }

}
