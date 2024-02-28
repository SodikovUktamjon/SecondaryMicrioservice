package com.uktamjon.sodikov.services;

import com.uktamjon.sodikov.domains.summary.MonthlySummary;
import com.uktamjon.sodikov.domains.summary.Summary;
import com.uktamjon.sodikov.domains.summary.YearlySummary;

import com.uktamjon.sodikov.domains.trainer.Trainer;
import com.uktamjon.sodikov.enums.ActionType;
import com.uktamjon.sodikov.repositories.SummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final SummaryRepository summaryRepository;

    public void modifyWorkload(Trainer trainer) {
        Summary summary = summaryRepository.findById(trainer.getId()).orElseGet(() -> new Summary(trainer.getId(), trainer, new ArrayList<>()));
        YearlySummary yearlySummary = getYearlySummary(summary, trainer);
        MonthlySummary monthlySummary = getMonthlySummary(yearlySummary, trainer);
        updateMonthlySummary(monthlySummary, trainer);
        summaryRepository.save(summary);
    }


    private YearlySummary getYearlySummary(Summary summary, Trainer trainer) {
        return summary.getYearlySummaries().stream()
                .filter(yearlySummary -> yearlySummary.getYear().equals(Year.from(trainer.getStartDate())))
                .findFirst()
                .orElseGet(() -> createYearlySummary(summary, trainer));
    }

    private YearlySummary createYearlySummary(Summary summary, Trainer trainer) {
        YearlySummary yearlySummary = YearlySummary.builder()
                .year(Year.from(trainer.getStartDate()))
                .monthlySummaries(new ArrayList<>())
                .build();
        yearlySummary.setSummary(summary);
        summary.getYearlySummaries().add(yearlySummary);
        return yearlySummary;
    }

    private MonthlySummary getMonthlySummary(YearlySummary yearlySummary, Trainer trainer) {
        return yearlySummary.getMonthlySummaries().stream()
                .filter(monthlySummary -> monthlySummary.getMonth().equals(YearMonth.from(trainer.getStartDate()).getMonthValue()))
                .findFirst()
                .orElseGet(() -> createMonthlySummary(yearlySummary, trainer));
    }

    private MonthlySummary createMonthlySummary(YearlySummary yearlySummary, Trainer trainer) {
        MonthlySummary monthlySummary = MonthlySummary.builder()
                .month(YearMonth.from(trainer.getStartDate()).getMonthValue())
                .trainingSummaryDuration(0)
                .build();
        monthlySummary.setYearlySummary(yearlySummary);
        yearlySummary.getMonthlySummaries().add(monthlySummary);
        return monthlySummary;
    }

    private void updateMonthlySummary(MonthlySummary monthlySummary, Trainer trainer) {
        if (trainer.getActionType().equals(ActionType.ADD)) {
            monthlySummary.setTrainingSummaryDuration(monthlySummary.getTrainingSummaryDuration() + trainer.getDuration());
        } else {
            monthlySummary.setTrainingSummaryDuration(monthlySummary.getTrainingSummaryDuration() - trainer.getDuration());
        }
    }

    public Summary getSummaryByTrainerId(int trainerId){
        return summaryRepository.findByTrainerId(trainerId);
    }
}