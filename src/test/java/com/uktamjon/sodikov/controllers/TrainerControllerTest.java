package com.uktamjon.sodikov.controllers;

import com.uktamjon.sodikov.controllers.TrainerController;
import com.uktamjon.sodikov.domains.Trainer;
import com.uktamjon.sodikov.services.TrainerWorkloadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerControllerTest {

    @Mock
    private TrainerWorkloadService trainerWorkloadService;

    @InjectMocks
    private TrainerController trainerController;

    @Test
    public void testCreateTrainer() {
        Trainer inputTrainer = new Trainer();
        Trainer expectedTrainer = new Trainer();

        when(trainerWorkloadService.trainer(inputTrainer)).thenReturn(expectedTrainer);

        ResponseEntity<Trainer> responseEntity = trainerController.createTrainer(inputTrainer);

        assertEquals(expectedTrainer, responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(trainerWorkloadService, times(1)).trainer(inputTrainer);
    }

    @Test
    public void testDeleteTrainer() {
        int trainerId = 123;

        ResponseEntity<Void> responseEntity = trainerController.deleteTrainer(trainerId);

        assertEquals(200, responseEntity.getStatusCodeValue());
        verify(trainerWorkloadService, times(1)).deleteTrainer(trainerId);
    }

}
