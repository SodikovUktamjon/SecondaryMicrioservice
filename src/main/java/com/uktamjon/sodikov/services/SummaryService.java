package com.uktamjon.sodikov.services;

import com.uktamjon.sodikov.domains.Trainer;
import com.uktamjon.sodikov.domains.summary.MonthlySummary;
import com.uktamjon.sodikov.domains.summary.Summary;
import com.uktamjon.sodikov.domains.summary.YearlySummary;
import com.uktamjon.sodikov.enums.ActionType;
import com.uktamjon.sodikov.repositories.SummaryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SummaryService {


    private final SummaryRepository summaryRepository;

    public Summary getSummaries(int trainerId){
        return summaryRepository.findByTrainerId(trainerId);
    }



    public void modify(Trainer trainer) {
       if(trainer.getActionType().equals(ActionType.DELETE)){
           summaryRepository.deleteAllByTrainerId(trainer.getId());
           return;
       }
        Summary summaries = getSummaries(trainer.getId());
        summaries.getYearlySummary().add(
                 YearlySummary.builder()
                         .summaries(List.of(MonthlySummary.builder()
                                         .date(trainer.getLocalDateTime())
                                         .summaries(Collections.singletonList("YourClass{" +
                                                 "username='" + trainer.getUsername() + '\'' +
                                                 ", firstName='" + trainer.getFirstName() + '\'' +
                                                 ", localDateTime=" + trainer.getLocalDateTime() +
                                                 ", duration=" + trainer.getDuration() +
                                                 '}'))
                                 .build()))
                         .build());
      summaryRepository.save(summaries);
    }
}
