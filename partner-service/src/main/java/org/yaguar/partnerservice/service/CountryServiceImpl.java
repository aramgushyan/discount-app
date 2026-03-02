package org.yaguar.partnerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponseLong;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.api.dto.response.ResultStatus;
import org.yaguar.partnerservice.mapper.CountryMapper;
import org.yaguar.partnerservice.repository.CountryRepository;

@Service
@RequiredArgsConstructor
class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public Result<Long> createCountry(CountryAddRequest countryAddRequest) {
        var countryEntity = countryMapper.toEntity(countryAddRequest);
        var countryResponse = countryMapper.toResponse(countryRepository.save(countryEntity));

        return new Result<>(countryResponse.id(), null, ResultStatus.CREATED);
    }

    @Override
    public Result<Void> deleteCountryById(Long id) {
        var optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            return new Result<>(null, "Country not found", ResultStatus.NOT_FOUND);
        }

        countryRepository.delete(optionalCountry.get());
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }

    @Override
    public Result<Page<CountryResponseLong>> findCountries(Pageable pageable) {
        var pageCountries = countryRepository.findAll(pageable);
        var pageResponses = pageCountries.map(countryMapper::toResponse);
        return new Result<>(pageResponses, null, ResultStatus.SUCCESS);
    }

    @Override
    public Result<CountryResponseLong> findCountryById(Long id) {
        return countryRepository.findById(id)
                .map(country -> new Result<>(countryMapper.toResponse(country), null, ResultStatus.SUCCESS))
                .orElseGet(() -> new Result<>(null, "Country not found", ResultStatus.NOT_FOUND));
    }

    @Override
    public Result<Void> updateCountry(Long id, CountryUpdateRequest countryUpdateRequest) {
        var optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            return new Result<>(null, "Country not found", ResultStatus.NOT_FOUND);
        }

        var countryEntity = optionalCountry.get();
        countryMapper.updateCountry(countryUpdateRequest, countryEntity);

        countryRepository.save(countryEntity);
        return new Result<>(null, null, ResultStatus.NO_CONTENT);
    }

}
