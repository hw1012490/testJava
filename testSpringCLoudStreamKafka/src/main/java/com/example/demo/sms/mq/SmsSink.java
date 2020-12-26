/**
 * @Company 杭州海康威视系统技术有限公司
 * @Deptment 公安事业部
 * @Copyright Copyright (c) 2013
 * @author niedaoxin
 * @version 1.0
 */
package com.example.demo.sms.mq;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author niedaoxin 2018年05月21日 18:58
 * @version V1.0
 * @modify {原因} by niedaoxin 2018年05月21日 18:58
 */
public interface SmsSink {

    String SMS_INPUT = "smsInput";
    String SMS_OUTPUT = "smsInput";

    @Input(SmsSink.SMS_INPUT)
    SubscribableChannel smsInput();

    @Output(SmsSink.SMS_OUTPUT)
    MessageChannel smsOutput();
}
