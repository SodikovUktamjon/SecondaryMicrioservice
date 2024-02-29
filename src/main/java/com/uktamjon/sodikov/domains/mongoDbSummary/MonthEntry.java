package com.uktamjon.sodikov.domains.mongoDbSummary;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthEntry {
    private String month;
    private int trainingsSummaryDuration;
}