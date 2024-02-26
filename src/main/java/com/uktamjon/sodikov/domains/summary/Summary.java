package com.uktamjon.sodikov.domains.summary;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int trainerId;
    @ElementCollection
    private List<YearlySummary> yearlySummary;

}
