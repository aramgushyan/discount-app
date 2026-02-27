package org.yaguar.partnerservice.api.dto.response;

import java.util.List;

public record CountryResponse(Long id, String name, List<RegionResponse> regions) {
}
