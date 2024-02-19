package com.uktamjon.sodikov.summary;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YearlySummary {
    private LocalDateTime year;
    private List<MonthlySummary> monthlySummaries;
}
