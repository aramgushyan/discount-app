package org.yaguar.authservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginRequest(@NotBlank @Length(min = 50) String username,
                           @NotBlank @Length(min = 8) String password) {
}
