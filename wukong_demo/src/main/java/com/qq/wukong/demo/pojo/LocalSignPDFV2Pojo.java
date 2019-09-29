package com.qq.wukong.demo.pojo;

import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.SignPDFFileBean;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 14:17
 * @Description:
 */
public class LocalSignPDFV2Pojo {
    private SignPDFFileBean file;
    private PosBean signPos;
    private String sealId;
    private String sealData;
    private int signType;

    public SignPDFFileBean getFile() {
        return file;
    }

    public void setFile(SignPDFFileBean file) {
        this.file = file;
    }

    public PosBean getSignPos() {
        return signPos;
    }

    public void setSignPos(PosBean signPos) {
        this.signPos = signPos;
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    public String getSealData() {
        return sealData;
    }

    public void setSealData(String sealData) {
        this.sealData = sealData;
    }

    public int getSignType() {
        return signType;
    }

    public void setSignType(int signType) {
        this.signType = signType;
    }
}
