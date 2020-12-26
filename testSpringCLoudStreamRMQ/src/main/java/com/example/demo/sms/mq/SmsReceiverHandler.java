package com.example.demo.sms.mq;

import com.example.demo.sms.dao.entity.QNotifySms;
import com.example.demo.sms.dao.repository.QNotifySmsRepository;
import com.example.demo.sms.tool.JacksonTool;
import com.example.demo.sms.tool.SmsSupport;
import com.example.demo.sms.tool.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class SmsReceiverHandler extends AbstractHandler<String, String> {


    @Autowired
    private QNotifySmsRepository qNotifySmsRepository;


    @Value(value = "${notify.sms.ip}")
    private String smsIp;
    @Value(value = "${notify.sms.port}")
    private String smsPort;
    @Value(value = "${notify.sms.encode}")
    private String smsEnc;
    @Value(value = "${notify.sms.period}")
    private Integer period;
    @Value(value = "${notify.sms.initialDelay}")
    private Integer initalDelay;

    private Boolean sendMessage(String phones, String content, String collectTime) {
        try {
            content = "[级联推送]" + content;
            StringBuilder urlBuilder = new StringBuilder("http://");
            urlBuilder.append(smsIp).append(":").append(smsPort).append("/sms.asp?phone=").append(phones).append("&con=").append(
                    URLEncoder.encode(content, smsEnc));
            String returnStr = SmsSupport.getHTML(urlBuilder.toString());
            return !StringUtils.isEmpty(returnStr);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    @Override
    protected void doAction(String message, String channel) {
        //目前就这一种类型
        if (!channel.equals(SmsSink.SMS_INPUT)) {
            return;
        }
        // 同样先存到数据库
        QNotifySms qNotifySms = JacksonTool.json2Bean(message, QNotifySms.class);
        if (qNotifySms == null) {
            return;
        }
        qNotifySms.setUpdateTime(TimeUtil.getCurrentUTCOfYMDHMS());
        qNotifySms.setContent("[级联推送]"+qNotifySms.getContent());
        //从rmq来的一定是推送上来的报警
        qNotifySms.setSmsFromDown(true);
        qNotifySmsRepository.save(qNotifySms);
        sendMessage(qNotifySms.getPhones(), qNotifySms.getContent(),null);
    }
}
