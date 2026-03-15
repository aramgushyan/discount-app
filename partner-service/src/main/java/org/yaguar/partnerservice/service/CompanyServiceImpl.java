package org.yaguar.partnerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaguar.partnerservice.api.dto.request.CompanyAddRequest;
import org.yaguar.partnerservice.api.dto.request.CompanyUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseLong;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.api.dto.response.ResultStatus;
import org.yaguar.partnerservice.entity.Status;
import org.yaguar.partnerservice.mapper.CompanyMapper;
import org.yaguar.partnerservice.repository.CompanyRepository;

@Service
@RequiredArgsConstructor
class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;


    @Override
    public Result<Page<CompanyResponseShort>> findAll(Pageable pageable) {
        var pagesCountries = companyRepository.findAllByStatus(pageable, Status.Active);
        var pageResponses = pagesCountries.map(companyMapper::toResponse);
        return new Result<>(pageResponses, null, ResultStatus.SUCCESS);
    }

    @Override
    public Result<CompanyResponseLong> findById(Long id) {
        return companyRepository.findByIdAndStatus(id, Status.Active)
                .map(companyEntity -> new Result<>(companyMapper.toLongResponse(companyEntity),
                        null, ResultStatus.SUCCESS))
                .orElse(new Result<>(null, "Company not found", ResultStatus.NOT_FOUND));
    }

    @Override
    public Result<Long> createCompany(CompanyAddRequest companyAddRequest) {
        var id = companyRepository.save(companyMapper.toEntity(companyAddRequest)).getId();
        return new Result<>(id, null, ResultStatus.CREATED);
    }

    @Override
    public Result<Void> updateCompany(Long id, CompanyUpdateRequest companyUpdateRequest) {
        var optionalCompany = companyRepository.findByIdAndStatus(id, Status.Active);
        if (optionalCompany.isEmpty()) {
            return new Result<>(null, "Company not found", ResultStatus.NOT_FOUND);
        }

        var companyEntity = optionalCompany.get();

        companyMapper.update(companyUpdateRequest, companyEntity);

        companyRepository.save(companyEntity);
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }

    @Override
    public Result<Void> deleteCompany(Long id) {
        var companyEntity = companyRepository.findById(id);
        if (companyEntity.isEmpty()) {
            return new Result<>(null, "Company not found", ResultStatus.NOT_FOUND);
        }

        companyRepository.delete(companyEntity.get());
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }
}
