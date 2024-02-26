package com.uktamjon.sodikov.domains;

import com.uktamjon.sodikov.enums.ActionType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trainer {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private LocalDateTime localDateTime;
    private int duration;
    private ActionType actionType;
}
