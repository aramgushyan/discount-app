package org.yaguar.partnerservice.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.yaguar.partnerservice.api.dto.request.UnitAddRequest;
import org.yaguar.partnerservice.api.dto.request.UnitUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.api.dto.response.UnitResponse;

public interface UnitService {
    Result<Page<UnitResponse>> findAll(Pageable pageable);

    Result<UnitResponse> findById(Long id);

    Result<Long> createUnit(UnitAddRequest unitAddRequest);

    Result<Void> deleteById(Long id);

    Result<Void> updateById(Long id, UnitUpdateRequest unitUpdateRequest);
}
