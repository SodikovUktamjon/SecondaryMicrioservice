package com.uktamjon.sodikov.domains.summary;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"summary_id", "year"}))

public class YearlySummary {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="summary_id", nullable=false)
    private Summary summary;

    private Year year;

    @OneToMany(mappedBy = "yearlySummary", cascade = CascadeType.ALL)
    private List<MonthlySummary> monthlySummaries;
}
