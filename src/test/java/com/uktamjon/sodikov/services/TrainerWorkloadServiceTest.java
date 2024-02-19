package com.uktamjon.sodikov.services;

import com.uktamjon.sodikov.domains.Trainer;
import com.uktamjon.sodikov.repositories.TrainerRepository;
import com.uktamjon.sodikov.services.TrainerWorkloadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerWorkloadServiceTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerWorkloadService trainerWorkloadService;

    @Test
    public void testTrainer() {
        Trainer inputTrainer = new Trainer();
        Trainer expectedSavedTrainer = new Trainer();

        when(trainerRepository.save(inputTrainer)).thenReturn(expectedSavedTrainer);

        Trainer resultTrainer = trainerWorkloadService.trainer(inputTrainer);

        verify(trainerRepository, times(1)).save(inputTrainer);
        assertEquals(expectedSavedTrainer, resultTrainer);
    }

    @Test
    public void testDeleteTrainer() {
        int trainerId = 123;
        trainerWorkloadService.deleteTrainer(trainerId);
        verify(trainerRepository, times(1)).deleteById(trainerId);
    }




}
