package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.yaguar.partnerservice.api.dto.response.RegionResponse;
import org.yaguar.partnerservice.entity.RegionEntity;

@Mapper(componentModel = "spring")
public interface RegionMapper {

    RegionResponse toResponse(RegionEntity region);
}
