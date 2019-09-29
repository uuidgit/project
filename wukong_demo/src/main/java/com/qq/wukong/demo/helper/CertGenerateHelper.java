package com.qq.wukong.demo.helper;

import com.qq.wukong.demo.exception.BizException;
import com.timevale.esign.sdk.tech.bean.*;
import com.timevale.esign.sdk.tech.bean.result.AddAccountResult;
import com.timevale.esign.sdk.tech.bean.result.EventCertResult;
import com.timevale.esign.sdk.tech.bean.result.GetAccountProfileResult;
import com.timevale.esign.sdk.tech.bean.result.Result;
import com.timevale.esign.sdk.tech.impl.constants.DeleteParamType;
import com.timevale.esign.sdk.tech.impl.constants.LicenseQueryType;
import com.timevale.esign.sdk.tech.service.AccountService;
import com.timevale.esign.sdk.tech.service.EventCertService;
import com.timevale.esign.sdk.tech.v3.client.ServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.text.MessageFormat;
import java.util.List;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 15:57
 * @Description: 2 证书生成服务
 */
public class CertGenerateHelper {
    private static final Logger logger = LoggerFactory.getLogger(CertGenerateHelper.class);
    // 账户对外接口类
    private AccountService accountService;
    private EventCertService eventCertService;

    public CertGenerateHelper(ServiceClient serviceClient) {
        accountService = serviceClient.accountService();
        eventCertService = serviceClient.eventCertService();
    }


    /**
     * @param person {@link PersonBean} 创建个人账户对象信息
     * @description: 2.1 创建个人账户，所创建账户是半实名，即在快捷签对接项目中可以正常签署，在e签宝平台中无法使用，必须重新通过实名认证后才可以正常使用。
     * @auther: 天雪
     * @date: 2019/9/27 16:22
     * @return: {@link AddAccountResult}
     * @since: JDK1.8
     */
    public AddAccountResult addAccount(PersonBean person)
            throws BizException {
        // 调用e签包私有接口
        AddAccountResult acctRst = accountService.addAccount(person);
        // 校验方法调用结果
        return checkAccountResult(acctRst, "个人", "创建");
    }

    /**
     * @param organize {@link OrganizeBean} 创建企业账户对象信息
     * @description: 2.2 创建企业账户，所创建账户是半实名的，即在快捷签对接项目中可以正常签署，在e签宝平台中无法使用，必须重新通过实名认证后才可以正常使用。
     * @auther: 天雪
     * @date: 2019/9/27 16:22
     * @return: {@link AddAccountResult}
     * @since: JDK1.8
     */
    public AddAccountResult addAccount(OrganizeBean organize)
            throws BizException {
        // 调用e签包私有接口
        AddAccountResult acctRst = accountService.addAccount(organize);
        // 校验方法调用结果
        return checkAccountResult(acctRst, "企业", "创建");
    }

    /**
     * @param accountId       {@link String} 待更新账号的标识
     * @param person          {@link UpdateOrganizeBean} 更新的个人信息详情
     * @param deleteParamType {@link List} 待置空的属性集合
     * @description: 2.3 更新个人账户,只有此账户的创建者才有权限更改账户信息，用户归属地（personArea）和身份证号（idNo）不允许修改。若修改了姓名，将自动为用户重发数字证书。
     * @auther: 天雪
     * @date: 2019/9/27 16:52
     * @since: JDK1.8
     */
    public void updateAccount(String accountId, UpdatePersonBean person, List<DeleteParamType> deleteParamType)
            throws BizException {
        // 调用e签包私有接口
        Result rst = accountService.updateAccount(accountId, person, deleteParamType);
        AddAccountResult result = new AddAccountResult();
        BeanUtils.copyProperties(rst, result);
        result.setAccountId(accountId);
        // 校验方法调用结果
        checkAccountResult(result, "个人", "更新");
    }

    /**
     * @param accountId       {@link String} 待更新账号的标识
     * @param organize        {@link UpdateOrganizeBean} 更新的企业信息详情
     * @param deleteParamType {@link List} 待置空的属性集合
     * @description: 2.4 更新企业账户,只有此账户的创建者才有权限更改账户信息，企业注册类型（regType）和企业证件号（organCode）不允许修改。若更改企业名称，将自动为用户重发数字证书。
     * @auther: 天雪
     * @date: 2019/9/27 16:52
     * @since: JDK1.8
     */
    public void updateAccount(String accountId, UpdateOrganizeBean organize, List<DeleteParamType> deleteParamType)
            throws BizException {
        // 调用e签包私有接口
        Result rst = accountService.updateAccount(accountId, organize, deleteParamType);
        AddAccountResult result = new AddAccountResult();
        BeanUtils.copyProperties(rst, result);
        result.setAccountId(accountId);
        // 校验方法调用结果
        checkAccountResult(result, "企业", "更新");
    }

    /**
     * @param accountId {@link String} 待注销账号的标识
     * @description: 注销账户，注销后账户将不可再使用，请谨慎调用。
     * @auther: 天雪
     * @date: 2019/9/27 17:05
     * @since: JDK1.8
     */
    public void deleteAccount(String accountId)
            throws BizException {
        // 调用e签包私有接口
        Result rst = accountService.deleteAccount(accountId);
        AddAccountResult result = new AddAccountResult();
        BeanUtils.copyProperties(rst, result);
        result.setAccountId(accountId);
        // 校验方法调用结果
        checkAccountResult(result, "", "注销");
    }

    /**
     * @description: 2.6 创建事件证书,事件证书是将签署者与行为绑定后并固化至数字证书中而形成的一种数字证书，事件证书证明了事件本身，不仅简化了电子数据取证过程的复杂度，还提升了电子数据的证据效力。同时，事件证书具有单次有效性，即使用证书完成签署后，此证书立即失效，不可重复使用。事件证书可以关联多个签署者。
     * @auther: 天雪
     * @date: 2019/9/27 17:16
     * @return:
     * @since: JDK1.8
     */
    public EventCertResult addEventCert(EventBean event) throws BizException {
        // 调用e签包私有接口
        EventCertResult certResult = eventCertService.addEventCert(event);
        // 校验方法调用结果
        return checkEventCertResult(certResult);
    }

    /**
     * @param idNo     {@link String} 待查询的证件号码
     * @param idNoType {@link String} 帐号对应的类型
     * @description: 2.7 根据证件号获取账户信息,只能获取自己项目下的帐号信息
     * @auther: 天雪
     * @date: 2019/9/27 17:31
     * @return: {@link GetAccountProfileResult}
     * @since: JDK1.8
     */
    public GetAccountProfileResult getAccountInfoByIdNo(String idNo, LicenseQueryType idNoType) throws BizException {
        // 调用e签包私有接口
        GetAccountProfileResult acctRst = accountService.getAccountInfoByIdNo(idNo, idNoType);
        // 校验方法调用结果
        return checkAccountProfileResult(acctRst);
    }

    /**
     * @param acctRst {@link GetAccountProfileResult} 根据证件号获取账户信息返回结果
     * @description: 校验根据证件号获取账户信息结果正确性
     * @auther: 天雪
     * @date: 2019/9/27 16:28
     * @return: {@link GetAccountProfileResult}
     * @since: JDK1.8
     */
    private GetAccountProfileResult checkAccountProfileResult(GetAccountProfileResult acctRst) throws BizException {
        if (acctRst.getErrCode() != 0) {
            String errMsg = MessageFormat.format("查询账号信息失败：errCode={0},msg={1}", acctRst.getErrCode(), acctRst.getMsg());
            throw new BizException(errMsg);
        }
        AccountProfile acctProfile = acctRst.getAccountInfo();
        logger.info("查询账号成功：账号标识accountId = {},名称name={},证件号idNo={},"
                        + "证件类型idNoType={},绑定手机号mobile={}",
                acctProfile.getAccountUid(), acctProfile.getName(), acctProfile.getIdNo(),
                acctProfile.getIdNoType(), acctProfile.getMobile());
        return acctRst;
    }

    /**
     * @param certResult {@link EventCertResult} 创建事件证书返回结果
     * @description: 校验创建事件证书结果正确性
     * @auther: 天雪
     * @date: 2019/9/27 16:28
     * @return: {@link EventCertResult}
     * @since: JDK1.8
     */
    private EventCertResult checkEventCertResult(EventCertResult certResult) throws BizException {
        if (certResult.getErrCode() != 0) {
            String errMsg = MessageFormat.format("创建事件证书失败：errCode = {1},msg = {2}",
                    certResult.getErrCode(), certResult.getMsg());
            throw new BizException(errMsg);
        }
        logger.info("创建事件证书:certId={},请妥善保管certId以便后续场景使用", certResult.getCertId());
        return certResult;
    }

    /**
     * @param acctRst   {@link AddAccountResult} 操作账户返回结果
     * @param type      {@link String} 账户类型 (个人或者企业)
     * @param operation {@link String} 操作类型 (CRUD)
     * @description: 校验账户创建结果正确性
     * @auther: 天雪
     * @date: 2019/9/27 16:28
     * @return: {@link AddAccountResult}
     * @since: JDK1.8
     */
    private AddAccountResult checkAccountResult(AddAccountResult acctRst, String type, String operation) throws BizException {
        if (acctRst.getErrCode() != 0) {
            String errMsg = MessageFormat.format("{0}{1}账号失败：errCode = {2},msg = {3}", operation, type,
                    acctRst.getErrCode(), acctRst.getMsg());
            throw new BizException(errMsg);
        }
        logger.info("{}{}账号成功:accountId={},请妥善保管AccountId以便后续场景存证使用", operation, type, acctRst.getAccountId());
        return acctRst;
    }
}
