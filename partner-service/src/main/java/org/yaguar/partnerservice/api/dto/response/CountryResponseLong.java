package org.yaguar.partnerservice.api.dto.response;

import java.util.List;

public record CountryResponseLong(Long id, String name, List<RegionResponseShort> regions) {
}
