package com.example.secondarymicrioservice;

import com.example.secondarymicrioservice.Trainer;
import com.example.secondarymicrioservice.TrainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerRepositoryTest {

    @Mock
    private TrainerRepository trainerRepository;

    @InjectMocks
    private TrainerWorkloadService trainerService;

    @Test
    public void testDeleteById() {
        int trainerId = 123;

        trainerService.deleteTrainer(trainerId);

        verify(trainerRepository, times(1)).deleteById(trainerId);
    }

}
