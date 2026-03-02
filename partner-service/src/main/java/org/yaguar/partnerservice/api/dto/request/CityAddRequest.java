package org.yaguar.partnerservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;

public record CityAddRequest(@NotNull @Positive Long id, @NotBlank String name, @NonNull @Positive Long regionId) {
}
