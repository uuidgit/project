package com.qq.wukong.demo.controller;

import com.qq.wukong.demo.exception.BizException;
import com.qq.wukong.demo.helper.CertGenerateHelper;
import com.qq.wukong.demo.helper.InitClientHelper;
import com.qq.wukong.demo.pojo.AddEventCertPojo;
import com.qq.wukong.demo.pojo.EventTargetPojo;
import com.qq.wukong.demo.pojo.UpdateAccountPojo;
import com.qq.wukong.demo.utils.ResultVo;
import com.qq.wukong.demo.utils.ResultVoUtils;
import com.timevale.esign.sdk.tech.bean.EventBean;
import com.timevale.esign.sdk.tech.bean.OrganizeBean;
import com.timevale.esign.sdk.tech.bean.PersonBean;
import com.timevale.esign.sdk.tech.bean.result.AddAccountResult;
import com.timevale.esign.sdk.tech.bean.result.EventCertResult;
import com.timevale.esign.sdk.tech.bean.result.GetAccountProfileResult;
import com.timevale.esign.sdk.tech.impl.constants.LicenseQueryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 17:39
 * @Description: 2    证书生成服务
 */
@RequestMapping("/cert")
@RestController
public class CertGenerateController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CertGenerateController.class);

    /**
     * @param person {@link PersonBean} 创建个人账户对象信息
     * @description: 2.1 创建个人账户
     * @auther: 天雪
     * @date: 2019/9/27 16:22
     * @return: {@link AddAccountResult}
     * @since: JDK1.8
     */
    @PostMapping(value = "/person", consumes = "application/json;charset=UTF-8")
    public Object addAccount(@RequestBody PersonBean person) throws BizException {
        startPrint("addAccount person");
        CertGenerateHelper certGenerateHelper = new CertGenerateHelper(InitClientHelper.serviceClient);
        AddAccountResult result = certGenerateHelper.addAccount(person);
        endPrint("addAccount person");
        return result;
    }

    /**
     * @param organize {@link OrganizeBean} 创建企业账户对象信息
     * @description: 2.2 创建企业账户
     * @auther: 天雪
     * @date: 2019/9/27 16:22
     * @return: {@link AddAccountResult}
     * @since: JDK1.8
     */
    @PostMapping("/organize")
    public Object addAccount(@RequestBody OrganizeBean organize) throws BizException {
        startPrint("addAccount organize");
        CertGenerateHelper certGenerateHelper = new CertGenerateHelper(InitClientHelper.serviceClient);
        AddAccountResult result = certGenerateHelper.addAccount(organize);
        endPrint("addAccount organize");
        return result;
    }

    /**
     * @param accountId         {@link String} 待更新账号的标识
     * @param updateAccountPojo {@link UpdateAccountPojo} 更新个人账户对象信息
     * @description: 2.3 更新个人账户
     * @auther: 天雪
     * @date: 2019/9/27 16:22
     * @since: JDK1.8
     */
    @PutMapping("/person/{accountId}")
    public Object updatePersonAccount(@PathVariable("accountId") String accountId, @RequestBody UpdateAccountPojo updateAccountPojo) throws BizException {
        startPrint("update person account");
        CertGenerateHelper certGenerateHelper = new CertGenerateHelper(InitClientHelper.serviceClient);
        certGenerateHelper.updateAccount(accountId, updateAccountPojo.getPerson(), updateAccountPojo.getDeleteParamType());
        endPrint("update person account");
        return null;
    }

    /**
     * @param accountId         {@link String} 待更新账号的标识
     * @param updateAccountPojo {@link UpdateAccountPojo} 更新企业账户对象信息
     * @description: 2.4 更新企业账户
     * @auther: 天雪
     * @date: 2019/9/27 16:22
     * @since: JDK1.8
     */
    @PutMapping("/organize/{accountId}")
    public Object updateOrganizeAccount(@PathVariable("accountId") String accountId, @RequestBody UpdateAccountPojo updateAccountPojo) throws BizException {
        startPrint("update organize account");
        CertGenerateHelper certGenerateHelper = new CertGenerateHelper(InitClientHelper.serviceClient);
        certGenerateHelper.updateAccount(accountId, updateAccountPojo.getOrganize(), updateAccountPojo.getDeleteParamType());
        endPrint("update organize account");
        return null;
    }

//    /**
//     * @param accountId {@link String} 待注销账号的标识
//     * @description: 注销账户
//     * @auther: 天雪
//     * @date: 2019/9/27 17:05
//     * @since: JDK1.8
//     */
//    public ResultVo deleteAccount(String accountId)
//            throws BizException {
//
//    }

    /**
     * @description: 2.6 创建事件证书
     * @auther: 天雪
     * @date: 2019/9/27 17:16
     * @return:
     * @since: JDK1.8
     */
    @PostMapping
    public ResultVo<EventCertResult> addEventCert(@RequestBody AddEventCertPojo event) throws BizException {
        startPrint("addEventCert");
        CertGenerateHelper certGenerateHelper = new CertGenerateHelper(InitClientHelper.serviceClient);
        EventBean eventBean = new EventBean();
        eventBean.setContent(event.getContent());

        logger.info("json : {}", event.getObjects());
        List<EventTargetPojo> targets = event.getObjects();
        List<EventBean.EventTargetBean> beanList = new ArrayList<>();
        for (EventTargetPojo target : targets) {
            EventBean.EventTargetBean bean = new EventBean().createTargetInstance();
            BeanUtils.copyProperties(target, bean);
            beanList.add(bean);
        }
        eventBean.setObjects(beanList);

        EventCertResult certResult = certGenerateHelper.addEventCert(eventBean);
        endPrint("addEventCert");
        return ResultVoUtils.success(certResult);
    }

    /**
     * @param idNo     {@link String} 待查询的证件号码
     * @param idNoType {@link String} 帐号对应的类型
     * @description: 2.7 根据证件号获取账户信息
     * @auther: 天雪
     * @date: 2019/9/27 17:31
     * @return: {@link GetAccountProfileResult}
     * @since: JDK1.8
     */
    @GetMapping("/account")
    public ResultVo<GetAccountProfileResult> getAccountInfoByIdNo(@RequestParam("idNo") String idNo, @RequestParam("idNoType") int idNoType) throws BizException {
        logger.info("getAccountInfoByIdNo start...");
        CertGenerateHelper certGenerateHelper = new CertGenerateHelper(InitClientHelper.serviceClient);
        GetAccountProfileResult result = certGenerateHelper.getAccountInfoByIdNo(idNo, LicenseQueryType.of(idNoType));
        logger.info("getAccountInfoByIdNo end.");
        return ResultVoUtils.success(result);
    }
}
