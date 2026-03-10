package org.yaguar.partnerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.yaguar.partnerservice.api.dto.request.CompanyAddRequest;
import org.yaguar.partnerservice.api.dto.request.CompanyUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseLong;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseShort;
import org.yaguar.partnerservice.entity.CompanyEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = UnitMapper.class)
public interface CompanyMapper {
    CompanyEntity toEntity(CompanyAddRequest companyAddRequest);

    CompanyResponseShort toResponse(CompanyEntity companyEntity);

    CompanyResponseLong toLongResponse(CompanyEntity companyEntity);

    List<CompanyResponseShort> toResponseList(List<CompanyEntity> companyEntityList);

    void update(CompanyUpdateRequest companyUpdateRequest, @MappingTarget CompanyEntity companyEntity);
}
