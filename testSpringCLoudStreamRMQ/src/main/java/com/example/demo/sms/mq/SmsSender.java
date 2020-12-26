/**
 * @Company 杭州海康威视系统技术有限公司
 * @Deptment 公安事业部
 * @Copyright Copyright (c) 2013
 * @author niedaoxin
 * @version 1.0
 */
package com.example.demo.sms.mq;

import com.example.demo.sms.dao.entity.NotifyConfig;
import com.example.demo.sms.dao.entity.QNotifySms;
import com.example.demo.sms.dao.repository.QNotifySmsRepository;
import com.example.demo.sms.tool.JacksonTool;
import com.example.demo.sms.tool.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 消费人脸kafka数据
 */

@Component
public class SmsSender {

    @Value(value = "${notify.sms.position}")
    private String smsPosition;

    @Autowired
    private SmsSink smsSink;

    @Autowired
    QNotifySmsRepository qNotifySmsRepository;

    public void StoreAndSendToUP(NotifyConfig notifyConfig) {
        QNotifySms qNotifySms = new QNotifySms();
        String time = TimeUtil.getCurrentUTCOfYMDHMS();
        //没有替换时间 就替换下，如果已经替换了不做任何处理
        String context = notifyConfig.getContent().replace("$time", time);
        qNotifySms.setCreateTime(time);
        qNotifySms.setUpdateTime(time);
        qNotifySms.setBusinessId(notifyConfig.getBusinessId());
        qNotifySms.setContent(context);
        qNotifySms.setPhones(notifyConfig.getPhones());
        qNotifySms.setGuid(UUID.randomUUID().toString());
        //本级（就是下级DOWN）也要保存发送的记录
        qNotifySms.setSmsFromDown(false);
        qNotifySmsRepository.save(qNotifySms);
        try {
            if (smsSink.smsOutput().send(MessageBuilder.withPayload(JacksonTool.bean2Json(qNotifySms)).build())) {
            } else {
            }
        } catch (Exception exp) {
        }
        return ;
    }

}
