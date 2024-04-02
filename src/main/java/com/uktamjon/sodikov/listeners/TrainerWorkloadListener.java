package com.uktamjon.sodikov.listeners;

import com.uktamjon.sodikov.domains.Trainer;
import com.uktamjon.sodikov.services.SummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TrainerWorkloadListener {

    private final SummaryService summaryService;

    @JmsListener(destination = "${activemq.queue.name}")
    public void receiveTrainerWorkload(Trainer trainerWorkload) {
        summaryService.modifyWorkload(trainerWorkload);
        log.info("Received TrainerWorkload: {}", trainerWorkload);
    }
}
