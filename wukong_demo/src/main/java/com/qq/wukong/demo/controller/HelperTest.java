package com.qq.wukong.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qq.wukong.demo.exception.BizException;
import com.qq.wukong.demo.helper.InitClientHelper;
import com.timevale.esign.sdk.tech.impl.constants.LegalAreaType;

/**
 * @Auther: 天雪
 * @Date: 2019/9/27 14:11
 * @Description: 业务中封装的辅助类测试
 */
public class HelperTest {
    public static void main(String[] args) throws BizException {
        // 注册客户端
        LegalAreaType type = LegalAreaType.of(19);

        System.out.println(type);
        type = of(19);
        System.out.println(type);

        JSONObject json = new JSONObject();
        json.put("13",123);

        System.out.println(JSON.parse(json.toString()).toString());
    }

    public static LegalAreaType of(Integer type) {
        LegalAreaType[] arr$;
        int len$ = (arr$ = LegalAreaType.values()).length;

        for (int i$ = 0; i$ < len$; ++i$) {
            LegalAreaType area = arr$[i$];
            System.out.println(area.type() + " : " + type);
            if (area.type().equals(type)) {
                return area;
            }
        }

        return null;
    }
}
