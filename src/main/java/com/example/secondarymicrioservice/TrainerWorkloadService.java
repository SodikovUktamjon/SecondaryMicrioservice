package com.example.secondarymicrioservice;

import com.example.secondarymicrioservice.summary.MonthlySummary;
import com.example.secondarymicrioservice.summary.Summary;
import com.example.secondarymicrioservice.summary.YearlySummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerWorkloadService {

    private final TrainerRepository trainerRepository;

    public Trainer trainer(Trainer trainer){
        return trainerRepository.save(trainer);
    }
    public void deleteTrainer(int id){
        trainerRepository.deleteById(id);
    }




    public Summary getSummary(int trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new RuntimeException("Trainer not found"));

        List<YearlySummary> yearlySummaries = generateYearlySummaries(trainer);

        return Summary.builder()
                .trainer(trainer)
                .yearlySummary(yearlySummaries)
                .build();
    }

    private List<YearlySummary> generateYearlySummaries(Trainer trainer) {
        int currentYear = LocalDateTime.now().getYear();
        return List.of(
                YearlySummary.builder()
                        .year(LocalDateTime.now())
                        .monthlySummaries(generateMonthlySummaries(trainer, currentYear))
                        .build(),
                YearlySummary.builder()
                        .year(LocalDateTime.now().minusYears(1))
                        .monthlySummaries(generateMonthlySummaries(trainer, currentYear - 1))
                        .build(),
                YearlySummary.builder()
                        .year(LocalDateTime.now().minusYears(2))
                        .monthlySummaries(generateMonthlySummaries(trainer, currentYear - 2))
                        .build()
        );
    }

    private List<MonthlySummary> generateMonthlySummaries(Trainer trainer, int year) {
        return List.of(
                MonthlySummary.builder()
                        .date(LocalDateTime.of(year, 1, 1, 0, 0))
                        .summaries(generateDailySummaries(trainer, year, 1))
                        .build(),
                MonthlySummary.builder()
                        .date(LocalDateTime.of(year, 2, 1, 0, 0))
                        .summaries(generateDailySummaries(trainer, year, 2))
                        .build()
        );
    }

    private List<String> generateDailySummaries(Trainer trainer, int year, int month) {
        int daysInMonth = LocalDateTime.of(year, month, 1, 0, 0).toLocalDate().lengthOfMonth();
        List<String> dailySummaries = new ArrayList<>();

        for (int day = 1; day <= daysInMonth; day++) {
            dailySummaries.add("Day " + day + " Summary");
        }

        return dailySummaries;
    }



}
