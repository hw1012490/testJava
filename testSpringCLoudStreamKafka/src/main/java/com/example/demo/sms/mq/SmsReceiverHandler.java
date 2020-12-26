package com.example.demo.sms.mq;

import org.springframework.stereotype.Service;

@Service
public class SmsReceiverHandler extends AbstractHandler<String, String> {


    @Override
    protected void doAction(String message, String channel) {
        //目前就这一种类型
        if (!channel.equals(SmsSink.SMS_INPUT)) {
            return;
        }
        System.out.println(message);
    }
}
