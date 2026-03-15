package org.yaguar.partnerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaguar.partnerservice.api.dto.request.UnitAddRequest;
import org.yaguar.partnerservice.api.dto.request.UnitUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.api.dto.response.ResultStatus;
import org.yaguar.partnerservice.api.dto.response.UnitResponse;
import org.yaguar.partnerservice.entity.Status;
import org.yaguar.partnerservice.mapper.UnitMapper;
import org.yaguar.partnerservice.repository.CityRepository;
import org.yaguar.partnerservice.repository.CompanyRepository;
import org.yaguar.partnerservice.repository.UnitRepository;

@Service
@RequiredArgsConstructor
class UnitServiceImpl implements UnitService {
    private final UnitRepository unitRepository;
    private final CompanyRepository companyRepository;
    private final CityRepository cityRepository;
    private final UnitMapper unitMapper;

    @Override
    public Result<Page<UnitResponse>> findAll(Pageable pageable) {
        var pages = unitRepository.findAllByStatus(pageable, Status.Active)
                .map(unitMapper::toResponse);
        return new Result<>(pages, null, ResultStatus.SUCCESS);
    }

    @Override
    public Result<UnitResponse> findById(Long id) {
        return unitRepository.findByIdAndStatus(id, Status.Active)
                .map(unitEntity -> new Result<>(unitMapper.toResponse(unitEntity), null, ResultStatus.SUCCESS))
                .orElseGet(() -> new Result<>(null, "Unit not found", ResultStatus.NOT_FOUND));
    }

    @Override
    public Result<Long> createUnit(UnitAddRequest unitAddRequest) {
        var company = companyRepository.findByIdAndStatus(unitAddRequest.companyId(), Status.Active);
        if (company.isEmpty()) {
            return new Result<>(null, "Company not found", ResultStatus.NOT_FOUND);
        }

        var city = cityRepository.findById(unitAddRequest.cityId());
        if (city.isEmpty()) {
            return new Result<>(null, "City not found", ResultStatus.NOT_FOUND);
        }

        var unit = unitMapper.toEntity(unitAddRequest, company.get(), city.get());
        var unitEntity = unitRepository.save(unit);
        return new Result<>(unitEntity.getId(), null, ResultStatus.CREATED);
    }

    @Override
    public Result<Void> deleteById(Long id) {
        var unit = unitRepository.findByIdAndStatus(id, Status.Active);
        if (unit.isEmpty()) {
            return new Result<>(null, "Unit not found", ResultStatus.NOT_FOUND);
        }

        unitRepository.delete(unit.get());
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }

    @Override
    public Result<Void> updateById(Long id, UnitUpdateRequest unitUpdateRequest) {
        var optionalUnit = unitRepository.findByIdAndStatus(id, Status.Active);
        if (optionalUnit.isEmpty()) {
            return new Result<>(null, "Unit not found", ResultStatus.NOT_FOUND);
        }

        var unit = optionalUnit.get();
        unitMapper.updateEntity(unitUpdateRequest, unit);

        unitRepository.save(unit);
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }
}
