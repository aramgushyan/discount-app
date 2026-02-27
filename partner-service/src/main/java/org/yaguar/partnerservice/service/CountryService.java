package org.yaguar.partnerservice.service;

import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponse;
import org.yaguar.partnerservice.api.dto.response.CountryResponseForList;
import org.yaguar.partnerservice.api.dto.response.Result;

import java.util.List;

public interface CountryService {
    public Result<CountryResponse> createCountry(CountryAddRequest countryAddRequest);
    public Result<Boolean>  deleteCountryById(Long id);
    public Result<List<CountryResponseForList>> findAllCountries();
    public Result<CountryResponse> findCountryById(Long id);
    public Result<Boolean> updateCountry(Long id, CountryUpdateRequest countryUpdateRequest);
}
