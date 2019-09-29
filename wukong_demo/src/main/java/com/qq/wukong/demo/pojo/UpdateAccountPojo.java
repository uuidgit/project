package com.qq.wukong.demo.pojo;

import com.timevale.esign.sdk.tech.bean.UpdateOrganizeBean;
import com.timevale.esign.sdk.tech.bean.UpdatePersonBean;
import com.timevale.esign.sdk.tech.impl.constants.DeleteParamType;

import java.util.List;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 18:04
 * @Description: 更新账户请求body参数映射对象
 */
public class UpdateAccountPojo {
    // 更新的个人信息详情
    private UpdatePersonBean person;
    // 更新的企业信息详情
    private UpdateOrganizeBean organize;
    // 待置空的属性集合
    private List<DeleteParamType> deleteParamType;

    public UpdatePersonBean getPerson() {
        return person;
    }

    public void setPerson(UpdatePersonBean person) {
        this.person = person;
    }

    public UpdateOrganizeBean getOrganize() {
        return organize;
    }

    public void setOrganize(UpdateOrganizeBean organize) {
        this.organize = organize;
    }

    public List<DeleteParamType> getDeleteParamType() {
        return deleteParamType;
    }

    public void setDeleteParamType(List<DeleteParamType> deleteParamType) {
        this.deleteParamType = deleteParamType;
    }
}
