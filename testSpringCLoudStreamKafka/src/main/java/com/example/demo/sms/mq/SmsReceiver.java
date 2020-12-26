/**
 * @Company 杭州海康威视系统技术有限公司
 * @Deptment 公安事业部
 * @Copyright Copyright (c) 2013
 * @author niedaoxin
 * @version 1.0
 */
package com.example.demo.sms.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 消费人脸kafka数据
 */
@EnableBinding(SmsSink.class)
@Component
public class SmsReceiver {


  @Autowired
  private SmsReceiverHandler smsReceiverHandler;

  @StreamListener(SmsSink.SMS_INPUT)
  public void receive(String message) {
    try {
      smsReceiverHandler.put(message, SmsSink.SMS_INPUT);

    } catch (Exception e) {
    }
  }
}
