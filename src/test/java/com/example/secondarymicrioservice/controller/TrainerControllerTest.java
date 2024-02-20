package com.example.secondarymicrioservice.controller;

import com.example.secondarymicrioservice.model.trainer.Trainer;
import com.example.secondarymicrioservice.service.TrainerWorkloadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerControllerTest {

    @Mock
    private TrainerWorkloadService trainerWorkloadService;

    @InjectMocks
    private TrainerController trainerController;

    private Trainer trainer;

    @BeforeEach
    public void setup() {
        trainer = new Trainer();
    }

    @Test
    public void modifyWorkload_callsServiceMethod() {
        trainerController.createTrainer(trainer);

        verify(trainerWorkloadService, times(1)).modifyWorkload(trainer);
    }

    @Test
    public void modifyWorkload_returnsOkResponse() {
        ResponseEntity<Void> response = trainerController.createTrainer(trainer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}