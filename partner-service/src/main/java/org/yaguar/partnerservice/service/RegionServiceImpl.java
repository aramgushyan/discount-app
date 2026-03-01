package org.yaguar.partnerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.yaguar.partnerservice.api.dto.request.RegionAddRequest;
import org.yaguar.partnerservice.api.dto.request.RegionUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.RegionResponseLong;
import org.yaguar.partnerservice.api.dto.response.RegionResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.api.dto.response.ResultStatus;
import org.yaguar.partnerservice.mapper.RegionMapper;
import org.yaguar.partnerservice.repository.CountryRepository;
import org.yaguar.partnerservice.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionMapper regionMapper;
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    @Override
    public Result<List<RegionResponseShort>> findAllRegions() {
        var regions = regionRepository.findAll();
        return new Result<>(regionMapper.toResponseShortList(regions), null, ResultStatus.SUCCESS);
    }

    @Override
    public Result<RegionResponseLong> findRegionById(Long id) {
        return regionRepository.findById(id)
                .map(regionEntity -> new Result<>(
                        regionMapper.toResponseLong(regionEntity), null, ResultStatus.SUCCESS))
                .orElseGet(() -> new Result<>(
                        null, "Region not found", ResultStatus.NOT_FOUND));
    }

    @Override
    public Result<Long> createRegion(RegionAddRequest regionAddRequest) {
        var optionalCountry = countryRepository.findById(regionAddRequest.countryId());
        if (optionalCountry.isEmpty()) {
            return new Result<>(null, "Country not found", ResultStatus.NOT_FOUND);
        }

        var region = regionMapper.toRegion(regionAddRequest);
        region.setCountry(optionalCountry.get());
        return new Result<>(regionRepository.save(region).getId(), null, ResultStatus.CREATED);
    }

    @Override
    public Result<Void> deleteRegionById(Long id) {
        var optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isEmpty()) {
            return new Result<>(null, "Region not found", ResultStatus.NOT_FOUND);
        }

        regionRepository.delete(optionalRegion.get());
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }

    @Override
    public Result<Void> updateRegion(Long id, RegionUpdateRequest regionUpdateRequest) {
        var optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isEmpty()) {
            return new Result<>(null, "Region not found", ResultStatus.NOT_FOUND);
        }

        var region = optionalRegion.get();
        regionMapper.updateRegion(regionUpdateRequest, region);
        regionRepository.save(region);
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }
}
