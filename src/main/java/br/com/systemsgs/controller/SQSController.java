package br.com.systemsgs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sqs")
public class SQSController {

    Logger logger = LoggerFactory.getLogger(SQSController.class);

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    private String endpoint;

    @GetMapping(value = "/{message}")
    public void sendMessageToQueue(@PathVariable String message){
        queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
    }

    @SqsListener("guilherme-sqs") /*nome da fila lá no SQS da AWS*/
    public void loadMessageFromSQS(String message){
        logger.info("message from SQS Queue {}", message);
    }

}
