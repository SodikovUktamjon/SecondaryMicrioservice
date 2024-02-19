package com.uktamjon.sodikov.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name="trainer_workload")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(unique = true, name="username")
    private String username;
    @Column(name="is_active")
    private boolean isActive;
    @Column(name="duration")
    private int duration;
    @Column(name="start_date")
    private LocalDateTime startDate;
}
