package com.gox.mqconsumer.controller;

import com.gox.mqconsumer.component.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping(path="send")
    public String startSending(){
        messageSender.start();
        return "Started";
    }

    @GetMapping(path="stop")
    public String stopSending(){
        messageSender.stop();
        return "Stopped";
    }
}
