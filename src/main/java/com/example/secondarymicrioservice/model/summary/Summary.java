package com.example.secondarymicrioservice.model.summary;

import com.example.secondarymicrioservice.model.trainer.Trainer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Summary {
    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @OneToMany(mappedBy = "summary", cascade = CascadeType.ALL)
    private List<YearlySummary> yearlySummaries;
}