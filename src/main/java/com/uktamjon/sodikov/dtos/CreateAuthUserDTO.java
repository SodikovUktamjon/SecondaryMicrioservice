package com.uktamjon.sodikov.dtos;

import lombok.*;

/**
 * A DTO for the {@link com.uktamjon.sodikov.domains.User} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreateAuthUserDTO{
        String username;
        String password;
        String confirmPassword;

}
