package org.yaguar.authservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;


public record RegisterRequest(
            @NotBlank @Length(max = 50) String username,
        @NotBlank @Length(max = 50, min = 8) String password,
        @NotBlank @Length(max = 50) String email) {
}
