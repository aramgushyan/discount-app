package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.yaguar.partnerservice.api.dto.request.CityAddRequest;
import org.yaguar.partnerservice.api.dto.request.CityUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CityResponseLong;
import org.yaguar.partnerservice.api.dto.response.CityResponseShort;
import org.yaguar.partnerservice.entity.CityEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityResponseShort toCityResponseShort(CityEntity city);

    @Mapping(source = "region.id", target = "regionId")
    CityResponseLong toCityResponseLong(CityEntity city);

    List<CityResponseShort> toCityResponseShortList(List<CityEntity> cityEntities);

    CityEntity toCityEntity(CityAddRequest cityAddRequest);

    void updateCityEntity(CityUpdateRequest request, @MappingTarget CityEntity city);
}
