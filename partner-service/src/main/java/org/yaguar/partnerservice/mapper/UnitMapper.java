package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.yaguar.partnerservice.api.dto.request.UnitAddRequest;
import org.yaguar.partnerservice.api.dto.request.UnitUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.UnitResponse;
import org.yaguar.partnerservice.entity.CityEntity;
import org.yaguar.partnerservice.entity.CompanyEntity;
import org.yaguar.partnerservice.entity.UnitEntity;

@Mapper(uses = {CityMapper.class, CompanyMapper.class})
public interface UnitMapper {
    @Mapping(target = "company", source = "company")
    @Mapping(target = "city", source = "city")
    UnitEntity toEntity(UnitAddRequest unitAddRequest, CompanyEntity company, CityEntity city);

    UnitResponse toResponse(UnitEntity unitEntity);

    void updateEntity(UnitUpdateRequest unitUpdateRequest, @MappingTarget UnitEntity unitEntity);
}
