package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.yaguar.partnerservice.api.dto.request.RegionAddRequest;
import org.yaguar.partnerservice.api.dto.request.RegionUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.RegionResponseLong;
import org.yaguar.partnerservice.api.dto.response.RegionResponseShort;
import org.yaguar.partnerservice.entity.RegionEntity;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface RegionMapper {
    RegionResponseShort toResponseShort(RegionEntity region);

    RegionResponseLong toResponseLong(RegionEntity region);

    RegionEntity toRegion(RegionAddRequest regionAddRequest);

    List<RegionResponseShort> toResponseShortList(List<RegionEntity> regions);

    void updateRegion(RegionUpdateRequest request, @MappingTarget RegionEntity region);
}
