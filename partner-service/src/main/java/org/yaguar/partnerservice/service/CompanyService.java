package org.yaguar.partnerservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yaguar.partnerservice.api.dto.request.CompanyAddRequest;
import org.yaguar.partnerservice.api.dto.request.CompanyUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseLong;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;

public interface CompanyService {
    Result<Page<CompanyResponseShort>> findAll(Pageable pageable);

    Result<CompanyResponseLong> findById(Long id);

    Result<Long> createCompany(CompanyAddRequest companyAddRequest);

    Result<Void> updateCompany(Long id,CompanyUpdateRequest companyUpdateRequest);

    Result<Void> deleteCompany(Long id);
}
