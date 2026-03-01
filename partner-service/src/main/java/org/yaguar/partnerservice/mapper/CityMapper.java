package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.yaguar.partnerservice.api.dto.response.CityResponse;
import org.yaguar.partnerservice.entity.CityEntity;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityResponse toCityResponse(CityEntity city);
}
