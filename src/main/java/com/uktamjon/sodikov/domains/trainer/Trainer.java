package com.uktamjon.sodikov.domains.trainer;

import com.uktamjon.sodikov.enums.ActionType;
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
    private Integer id;

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