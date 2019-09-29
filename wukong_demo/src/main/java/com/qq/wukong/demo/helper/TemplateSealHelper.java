package com.qq.wukong.demo.helper;

import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import com.timevale.esign.sdk.tech.bean.seal.OrganizeTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.PersonTemplateType;
import com.timevale.esign.sdk.tech.bean.seal.SealColor;
import com.timevale.esign.sdk.tech.service.SealService;
import com.timevale.esign.sdk.tech.v3.client.ServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 11:26
 * @Description: 3 模板印章生成服务
 */
public class TemplateSealHelper {
    private static final Logger logger = LoggerFactory.getLogger(TemplateSealHelper.class);
    private SealService sealService;

    public TemplateSealHelper(ServiceClient serviceClient) {
        sealService = serviceClient.sealService();
    }


    /**
     * @description: 3.1 创建个人模板印章
     * @auther: 天雪
     * @date: 2019/9/29 11:29
     * @return: {@link AddSealResult} 创建个人模板印章返回结果
     * @since: JDK1.8
     */
    public AddSealResult addTemplateSeal(String accountId, String templateType, String color) {
        AddSealResult sealResult = sealService.addTemplateSeal(accountId, personTemplateTypeOf(templateType), SealColor.from(color));
        return sealResult;
    }

    /**
     * @description: 3.2 创建企业模板印章
     * @auther: 天雪
     * @date: 2019/9/29 11:29
     * @return: {@link AddSealResult} 创建企业模板印章返回结果
     * @since: JDK1.8
     */
    public AddSealResult addTemplateSeal(String accountId, String templateType, String color, String hText, String qText) {
        AddSealResult sealResult = sealService.addTemplateSeal(accountId, organizeTemplateTypeOf(templateType), SealColor.from(color), hText, qText);
        return sealResult;
    }

    private PersonTemplateType personTemplateTypeOf(String templateType) {
        PersonTemplateType[] values = PersonTemplateType.values();
        for (PersonTemplateType value : values) {
            if (value.name().toUpperCase().equals(templateType)) {
                return value;
            }
        }
        return null;
    }

    private OrganizeTemplateType organizeTemplateTypeOf(String templateType) {
        OrganizeTemplateType[] values = OrganizeTemplateType.values();
        for (OrganizeTemplateType value : values) {
            if (value.name().toUpperCase().equals(templateType)) {
                return value;
            }
        }
        return null;
    }
}
