package org.yaguar.partnerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yaguar.partnerservice.api.dto.request.CityAddRequest;
import org.yaguar.partnerservice.api.dto.request.CityUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CityResponseLong;
import org.yaguar.partnerservice.api.dto.response.CityResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.api.dto.response.ResultStatus;
import org.yaguar.partnerservice.mapper.CityMapper;
import org.yaguar.partnerservice.repository.CityRepository;
import org.yaguar.partnerservice.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final RegionRepository regionRepository;

    @Override
    public Result<CityResponseLong> findCityById(Long id) {
        return cityRepository.findById(id)
                .map(entity -> new Result<>(cityMapper.toCityResponseLong(entity), null, ResultStatus.SUCCESS))
                .orElseGet(() -> new Result<>(null, "City not found", ResultStatus.NOT_FOUND));
    }

    @Override
    public Result<List<CityResponseShort>> findAllCities() {
        return new Result<>(cityMapper.toCityResponseShortList(cityRepository.findAll()), null, ResultStatus.SUCCESS);
    }

    @Transactional()
    @Override
    public Result<Long> createCity(CityAddRequest cityAddRequest) {
        var optionalRegion = regionRepository.findById(cityAddRequest.regionId());
        if (optionalRegion.isEmpty()) {
            return new Result<>(null, "Region not found", ResultStatus.NOT_FOUND);
        }

        var city = cityMapper.toCityEntity(cityAddRequest);
        city.setRegion(optionalRegion.get());
        return new Result<>(cityRepository.save(city).getId(), null, ResultStatus.CREATED);
    }

    @Override
    public Result<Void> updateCity(Long id, CityUpdateRequest cityUpdateRequest) {
        var optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            return new Result<>(null, "City not found", ResultStatus.NOT_FOUND);
        }

        var city = optionalCity.get();
        cityMapper.updateCityEntity(cityUpdateRequest, city);
        cityRepository.save(city);
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }

    @Override
    public Result<Void> deleteCityById(Long id) {
        var optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            return new Result<>(null, "City not found", ResultStatus.NOT_FOUND);
        }

        cityRepository.delete(optionalCity.get());
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }
}
