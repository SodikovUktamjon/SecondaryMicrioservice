package com.uktamjon.sodikov.domains.summary;

import com.uktamjon.sodikov.domains.trainer.Trainer;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Summary {
    @Id
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    private Trainer trainer;

    @OneToMany(mappedBy = "summary", cascade = CascadeType.ALL)
    private List<YearlySummary> yearlySummaries;
}
