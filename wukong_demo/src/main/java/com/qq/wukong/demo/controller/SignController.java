package com.qq.wukong.demo.controller;

import com.qq.wukong.demo.helper.InitClientHelper;
import com.qq.wukong.demo.helper.SignHelper;
import com.qq.wukong.demo.pojo.LocalSignPDFV2Pojo;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 14:12
 * @Description:
 */
@RequestMapping("/sign")
@RestController
public class SignController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SignController.class);

    @PostMapping()
    public Object localSignPDFV2(@RequestBody LocalSignPDFV2Pojo signPDFV2Pojo) {
        startPrint("localSignPDFV2");
        SignHelper signHelper = new SignHelper(InitClientHelper.serviceClient);
        FileDigestSignResult result = signHelper.localSignPDF(signPDFV2Pojo.getFile(), signPDFV2Pojo.getSignPos(), signPDFV2Pojo.getSealId(), signPDFV2Pojo.getSignType());
        endPrint("localSignPDFV2");
        return result;
    }

    @PostMapping("/img")
    public Object localSignPDF(@RequestBody LocalSignPDFV2Pojo signPDFV2Pojo) {
        startPrint("localSignPDF");
        SignHelper signHelper = new SignHelper(InitClientHelper.serviceClient);
        FileDigestSignResult result = signHelper.localSignPDF(signPDFV2Pojo.getFile(), signPDFV2Pojo.getSignPos(), signPDFV2Pojo.getSealData(), signPDFV2Pojo.getSignType());
        endPrint("localSignPDF");
        return result;
    }
}
