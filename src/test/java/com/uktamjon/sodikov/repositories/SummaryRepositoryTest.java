package com.uktamjon.sodikov.repositories;

import com.uktamjon.sodikov.domains.summary.Summary;
import com.uktamjon.sodikov.services.SummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SummaryRepositoryTest {

    @Mock
    private SummaryRepository summaryRepository;

    @InjectMocks
    private SummaryService summaryService;

    @Test
    public void testFindByTrainerId() {
        int trainerId = 1;
        Summary expectedSummary = new Summary();
        expectedSummary.setTrainerId(trainerId);

        when(summaryRepository.findByTrainerId(trainerId)).thenReturn(expectedSummary);

        Summary actualSummary = summaryService.getSummaries(trainerId);

        assertEquals(expectedSummary, actualSummary);

        verify(summaryRepository, times(1)).findByTrainerId(trainerId);
    }
}
