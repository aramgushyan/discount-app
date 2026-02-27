package org.yaguar.partnerservice.api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CountryAddRequest(@NotBlank String name) {
}
