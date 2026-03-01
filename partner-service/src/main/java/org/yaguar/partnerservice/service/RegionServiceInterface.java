package org.yaguar.partnerservice.service;

import org.yaguar.partnerservice.api.dto.request.RegionAddRequest;
import org.yaguar.partnerservice.api.dto.request.RegionUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.RegionResponseLong;
import org.yaguar.partnerservice.api.dto.response.RegionResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;

import java.util.List;

public interface RegionServiceInterface {
    Result<List<RegionResponseShort>> findAllRegions();

    Result<RegionResponseLong> findRegionById(Long id);

    Result<Long> createRegion(RegionAddRequest regionAddRequest);

    Result<Void> deleteRegionById(Long id);

    Result<Void> updateRegion(Long id, RegionUpdateRequest regionUpdateRequest);
}
