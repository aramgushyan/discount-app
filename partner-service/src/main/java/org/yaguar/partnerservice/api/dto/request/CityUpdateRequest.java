package org.yaguar.partnerservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CityUpdateRequest(@NotBlank String name) {
}
