package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponse;
import org.yaguar.partnerservice.api.dto.response.CountryResponseForList;
import org.yaguar.partnerservice.entity.CountryEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RegionMapper.class}) 
public interface CountryMapper {
    CountryResponse toResponse(CountryEntity country);
    CountryEntity toEntity(CountryAddRequest country);
    CountryResponseForList toResponseForList(CountryEntity country);
    List<CountryResponseForList> toResponseForList(List<CountryEntity> country);
    void updateCountry(CountryUpdateRequest countryUpdateRequest, @MappingTarget CountryEntity country);
}
