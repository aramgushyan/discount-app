package org.yaguar.partnerservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record RegionAddRequest(@NotBlank String name, @Positive Long countryId) {
}
