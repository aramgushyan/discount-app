package org.yaguar.partnerservice.api.dto.response;

import java.util.List;

public record RegionResponseLong(Long id, String name, List<CityResponseShort> cities, CountryResponseShort country) {
}
