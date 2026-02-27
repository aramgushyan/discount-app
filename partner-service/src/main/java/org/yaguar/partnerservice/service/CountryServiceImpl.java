package org.yaguar.partnerservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponse;
import org.yaguar.partnerservice.api.dto.response.CountryResponseForList;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.mapper.CountryMapper;
import org.yaguar.partnerservice.repository.CountryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public Result<CountryResponse> createCountry(CountryAddRequest countryAddRequest) {
        var countryEntity = countryMapper.toEntity(countryAddRequest);
        var countryResponse = countryMapper.toResponse(countryRepository.save(countryEntity));

        return new Result<>(countryResponse, null, HttpStatus.CREATED);
    }

    @Override
    public Result<Boolean> deleteCountryById(Long id) {
        var optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            return new Result<>(false, "Country not found", HttpStatus.NOT_FOUND);
        }

        countryRepository.delete(optionalCountry.get());
        return new Result<>(true, null, HttpStatus.OK);
    }

    @Override
    public Result<List<CountryResponseForList>> findAllCountries() {
        var countryList = countryMapper.toResponseForList(countryRepository.findAll());
        return new Result<>(countryList, null, HttpStatus.OK);
    }

    @Override
    public Result<CountryResponse> findCountryById(Long id) {
        return countryRepository.findById(id)
                .map(country -> new Result<>(countryMapper.toResponse(country), null, HttpStatus.OK))
                .orElseGet(() -> new Result<>(null, "Country not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Result<Boolean> updateCountry(Long id, CountryUpdateRequest countryUpdateRequest) {
        var optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            return new Result<>(false, "Country not found", HttpStatus.NOT_FOUND);
        }

        var countryEntity = optionalCountry.get();
        countryMapper.updateCountry(countryUpdateRequest, countryEntity);

        countryRepository.save(countryEntity);
        return new Result<>(true, null, HttpStatus.OK);
    }

}
