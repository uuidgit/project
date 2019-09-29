package com.qq.wukong.demo.controller;

import com.qq.wukong.demo.exception.BizException;
import com.qq.wukong.demo.helper.InitClientHelper;
import com.qq.wukong.demo.helper.TemplateSealHelper;
import com.qq.wukong.demo.pojo.TemplateSealPojo;
import com.timevale.esign.sdk.tech.bean.result.AddSealResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 11:39
 * @Description:
 */
@RequestMapping("/seal")
@RestController
public class TemplateSealController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TemplateSealController.class);

    @PostMapping("/person")
    public Object addPersonTemplateSeal(@RequestBody TemplateSealPojo sealPojo) throws BizException {
        startPrint("addPersonTemplateSeal");
        TemplateSealHelper sealHelper = new TemplateSealHelper(InitClientHelper.serviceClient);
        AddSealResult result = sealHelper.addTemplateSeal(sealPojo.getAccountId(), sealPojo.getTemplateType(), sealPojo.getColor());
        endPrint("addPersonTemplateSeal");
        return result;
    }


    @PostMapping("/organize")
    public Object addOrganizeTemplateSeal(@RequestBody TemplateSealPojo sealPojo) throws BizException {
        startPrint("addOrganizeTemplateSeal");
        TemplateSealHelper sealHelper = new TemplateSealHelper(InitClientHelper.serviceClient);
        AddSealResult result = sealHelper.addTemplateSeal(sealPojo.getAccountId(), sealPojo.getTemplateType(), sealPojo.getColor(), sealPojo.gethText(), sealPojo.getqText());
        endPrint("addOrganizeTemplateSeal");
        return result;
    }
}
