package com.qq.wukong.demo.helper;

import com.qq.wukong.demo.exception.BizException;
import com.qq.wukong.demo.utils.FileUtils;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.SignPDFFileBean;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.impl.constants.SignType;
import com.timevale.esign.sdk.tech.service.SelfSignService;
import com.timevale.esign.sdk.tech.v3.client.ServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 13:53
 * @Description:
 */
public class SignHelper {
    private static final Logger logger = LoggerFactory.getLogger(SignHelper.class);
    private SelfSignService selfSignService;

    public SignHelper(ServiceClient serviceClient) {
        selfSignService = serviceClient.selfSignService();
    }

    public FileDigestSignResult localSignPDFV2(SignPDFFileBean file, PosBean signPos, String sealId, int signType) {
        String p = file.getSrcPdfFile();
        try {
            file.setBytes(FileUtils.getFileBytes(p));
            file.setSrcPdfFile(null);
        } catch (BizException e) {
            e.printStackTrace();
        }
        return selfSignService.localSignPdfV2(file, signPos, sealId, SignType.from(signType));
    }

    public FileDigestSignResult localSignPDF(SignPDFFileBean file, PosBean signPos, String sealData, int signType) {
        String p = file.getSrcPdfFile();
        try {
            file.setBytes(FileUtils.getFileBytes(p));
            file.setSrcPdfFile(null);
        } catch (BizException e) {
            e.printStackTrace();
        }

        return selfSignService.localSignPdf(file, signPos, sealData, SignType.from(signType));
    }
}
