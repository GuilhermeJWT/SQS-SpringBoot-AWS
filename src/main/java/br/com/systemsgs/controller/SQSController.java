package br.com.systemsgs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sqs")
public class SQSController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    @GetMapping(value = "/{message}")
    public void sendMessageToQueue(@PathVariable String message){
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
    }

}