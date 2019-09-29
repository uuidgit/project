package com.qq.wukong.demo.pojo;

import com.timevale.esign.sdk.tech.bean.EventBean;

import java.util.List;

/**
 * @Auther: 天雪
 * @Date: 2019/9/29 10:36
 * @Description:
 */
public class AddEventCertPojo {
    private String content;
    private List<EventTargetPojo> objects;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<EventTargetPojo> getObjects() {
        return objects;
    }

    public void setObjects(List<EventTargetPojo> objects) {
        this.objects = objects;
    }

}
