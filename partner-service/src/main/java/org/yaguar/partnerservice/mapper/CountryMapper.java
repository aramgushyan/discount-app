package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponseLong;
import org.yaguar.partnerservice.api.dto.response.CountryResponseShort;
import org.yaguar.partnerservice.entity.CountryEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface CountryMapper {
    CountryResponseLong toResponse(CountryEntity country);

    CountryEntity toEntity(CountryAddRequest country);

    CountryResponseShort toResponseForList(CountryEntity country);

    List<CountryResponseShort> toResponseForList(List<CountryEntity> country);

    void updateCountry(CountryUpdateRequest countryUpdateRequest, @MappingTarget CountryEntity country);
}
