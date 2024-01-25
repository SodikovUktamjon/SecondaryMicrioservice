package com.example.secondarymicrioservice.summary;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlySummary {
    private LocalDateTime date;
    private List<String> summaries;
}
