package com.uktamjon.sodikov.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeadLetterQueueListener {

    @JmsListener(destination = "${activemq.dlq.name}")
    public void receiveFromDLQ(Object message) {
        log.warn("Received message from Dead Letter Queue: {}", message);
    }
}
