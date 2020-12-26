package com.example.demo;

import com.example.demo.sms.dao.entity.NotifyConfig;
import com.example.demo.sms.mq.SmsSender;
import com.example.demo.sms.tool.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {




    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        SmsSender smsSender = (SmsSender) SpringUtil.getBean("smsSender");
        //去执行sms
        NotifyConfig notifyConfig = new NotifyConfig();
        notifyConfig.setBusinessId("xalarm_facealarmsummary");
        notifyConfig.setContent("【业务巡检平台】于$time，检测人脸报警链路中组件状态失败!");
        notifyConfig.setPhones("18969196728");
        smsSender.StoreAndSendToUP(notifyConfig);


    }

}
