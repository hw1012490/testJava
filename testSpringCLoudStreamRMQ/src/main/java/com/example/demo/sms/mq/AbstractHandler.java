package com.example.demo.sms.mq;


import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public abstract class AbstractHandler<T, C> {

  public static ThreadPoolTaskExecutor rabbitExecutor;
  static{
    rabbitExecutor = new ThreadPoolTaskExecutor();
  /*  rabbitExecutor.setQueueCapacity(10000);
    rabbitExecutor.setCorePoolSize(5);
    rabbitExecutor.setMaxPoolSize(10);
    rabbitExecutor.setKeepAliveSeconds(5000);*/
    rabbitExecutor.initialize();
  }


  abstract protected void doAction(T t, C channel);

  public void put(T t, C channel) {
    rabbitExecutor.execute(() -> {
      doAction(t, channel);
    });
  }
}