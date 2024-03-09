package com.uktamjon.sodikov.listeners;

import com.uktamjon.sodikov.domains.trainer.Trainer;
import com.uktamjon.sodikov.services.SummaryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TrainerWorkloadListenerTest {

    @Mock
    private SummaryService summaryService;

    @InjectMocks
    private TrainerWorkloadListener trainerWorkloadListener;

}
