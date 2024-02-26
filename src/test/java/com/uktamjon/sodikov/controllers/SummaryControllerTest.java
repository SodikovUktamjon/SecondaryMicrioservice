package com.uktamjon.sodikov.controllers;

import com.uktamjon.sodikov.domains.Trainer;
import com.uktamjon.sodikov.domains.summary.Summary;
import com.uktamjon.sodikov.services.SummaryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SummaryControllerTest {

    @Mock
    private SummaryService summaryService;

    @InjectMocks
    private SummaryController summaryController;

    @Test
    public void testModify() {
        Trainer trainer = new Trainer();

        ResponseEntity<Void> responseEntity = summaryController.modify(trainer);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        verify(summaryService, times(1)).modify(trainer);
    }

    @Test
    public void testGetSummaries() {
        int trainerId = 1;
        Summary expectedSummary = new Summary();
        when(summaryService.getSummaries(trainerId)).thenReturn(expectedSummary);

        ResponseEntity<Summary> responseEntity = summaryController.getSummaries(trainerId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedSummary, responseEntity.getBody());
        verify(summaryService, times(1)).getSummaries(trainerId);
    }
}
