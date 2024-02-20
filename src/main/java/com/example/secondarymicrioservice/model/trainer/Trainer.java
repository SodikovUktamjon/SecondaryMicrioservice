package com.example.secondarymicrioservice.model.trainer;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Trainer {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private boolean isActive;
    @Transient
    private int duration;
    @Transient
    private LocalDateTime startDate;

    @Transient
    private ActionType actionType;
}