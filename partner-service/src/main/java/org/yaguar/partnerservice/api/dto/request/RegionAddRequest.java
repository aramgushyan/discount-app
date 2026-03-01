package org.yaguar.partnerservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RegionAddRequest(@Positive Long id, @NotBlank String name,@Positive Long countryId) {
}
