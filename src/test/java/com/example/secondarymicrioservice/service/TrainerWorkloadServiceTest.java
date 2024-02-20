package com.example.secondarymicrioservice.service;

import com.example.secondarymicrioservice.model.trainer.ActionType;
import com.example.secondarymicrioservice.model.trainer.Trainer;
import com.example.secondarymicrioservice.model.summary.MonthlySummary;
import com.example.secondarymicrioservice.model.summary.Summary;
import com.example.secondarymicrioservice.model.summary.YearlySummary;
import com.example.secondarymicrioservice.repository.SummaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerWorkloadServiceTest {

    @Mock
    private SummaryRepository summaryRepository;

    @InjectMocks
    private TrainerWorkloadService trainerWorkloadService;

    private Trainer trainer;
    private Summary summary;
    private YearlySummary yearlySummary;
    private MonthlySummary monthlySummary;

    @BeforeEach
    public void setup() {
        trainer = new Trainer();
        trainer.setId(1L);
        trainer.setStartDate(YearMonth.now().atDay(1).atStartOfDay());
        trainer.setDuration(60);
        trainer.setActionType(ActionType.ADD);

        monthlySummary = MonthlySummary.builder()
                                       .month(YearMonth.from(trainer.getStartDate()).getMonthValue())
                                       .trainingSummaryDuration(0)
                                       .build();

        yearlySummary = YearlySummary.builder()
                                     .year(Year.from(trainer.getStartDate()))
                                     .monthlySummaries(new ArrayList<>(List.of(monthlySummary)))
                                     .build();

        summary = new Summary(trainer.getId(), trainer, new ArrayList<>(List.of(yearlySummary)));
    }

    @Test
    public void modifyWorkload_addsDuration_whenActionTypeIsAdd() {
        when(summaryRepository.findById(1L)).thenReturn(Optional.of(summary));

        trainerWorkloadService.modifyWorkload(trainer);

        verify(summaryRepository, times(1)).save(summary);
        assertEquals(60, monthlySummary.getTrainingSummaryDuration());
    }

    @Test
    public void modifyWorkload_subtractsDuration_whenActionTypeIsSubtract() {
        trainer.setActionType(ActionType.DELETE);
        monthlySummary.setTrainingSummaryDuration(120);
        when(summaryRepository.findById(1L)).thenReturn(Optional.of(summary));

        trainerWorkloadService.modifyWorkload(trainer);

        verify(summaryRepository, times(1)).save(summary);
        assertEquals(60, monthlySummary.getTrainingSummaryDuration());
    }

    @Test
    public void modifyWorkload_createsNewSummary_whenSummaryDoesNotExist() {
        when(summaryRepository.findById(1L)).thenReturn(Optional.empty());

        trainerWorkloadService.modifyWorkload(trainer);

        verify(summaryRepository, times(1)).save(any(Summary.class));
    }

    @Test
    public void modifyWorkload_createsNewYearlySummary_whenYearlySummaryDoesNotExist() {
        summary.getYearlySummaries().clear();
        when(summaryRepository.findById(1L)).thenReturn(Optional.of(summary));

        trainerWorkloadService.modifyWorkload(trainer);

        verify(summaryRepository, times(1)).save(summary);
        assertEquals(1, summary.getYearlySummaries().size());
    }

    @Test
    public void modifyWorkload_createsNewMonthlySummary_whenMonthlySummaryDoesNotExist() {
        yearlySummary.getMonthlySummaries().clear();
        when(summaryRepository.findById(1L)).thenReturn(Optional.of(summary));

        trainerWorkloadService.modifyWorkload(trainer);

        verify(summaryRepository, times(1)).save(summary);
        assertEquals(1, yearlySummary.getMonthlySummaries().size());
    }
}