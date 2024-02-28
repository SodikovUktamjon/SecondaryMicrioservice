package com.uktamjon.sodikov.listeners;

import jakarta.jms.ObjectMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jms.core.JmsTemplate;

@ExtendWith(MockitoExtension.class)

class DeadLetterQueueListenerTest {

    @Mock
    private JmsTemplate jmsTemplate;

    @InjectMocks
    private DeadLetterQueueListener deadLetterQueueListener;

    @Test
    void testReceiveFromDLQ() throws Exception {
        ObjectMessage message = Mockito.mock(ObjectMessage.class);
        deadLetterQueueListener.receiveFromDLQ(message);
    }


}
