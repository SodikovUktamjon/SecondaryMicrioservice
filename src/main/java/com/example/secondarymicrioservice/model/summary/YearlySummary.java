package com.example.secondarymicrioservice.model.summary;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
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
