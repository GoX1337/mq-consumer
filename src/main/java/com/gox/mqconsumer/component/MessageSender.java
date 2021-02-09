package com.gox.mqconsumer.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    @Value("${ibm.mq.queueName}")
    private String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    private boolean sending = false;
    private int msgId = 0;

    public void start(){
        this.sending = true;
        new Thread(() -> {
            while(this.sending){
                jmsTemplate.convertAndSend(queueName, "Hi " + msgId++);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stop(){
        this.sending = false;
    }
}
