package org.yaguar.partnerservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UnitAddRequest(@Positive @NotNull Long cityId,
                             @NotBlank String address,
                             @Positive @NotNull Long adminId,
                             @NotBlank String email,
                             @NotBlank String phone) {
}
