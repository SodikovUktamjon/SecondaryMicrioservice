package com.uktamjon.sodikov.domains.summary;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Embeddable
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class YearlySummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDateTime year;
    @ElementCollection
    private List<MonthlySummary> summaries;
}
