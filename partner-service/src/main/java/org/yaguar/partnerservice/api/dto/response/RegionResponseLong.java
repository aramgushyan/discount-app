package org.yaguar.partnerservice.api.dto.response;

import java.util.List;

public record RegionResponseLong(Long id, String name, List<CityResponse> cities, CountryResponseShort country) {
}
