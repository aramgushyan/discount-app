package org.yaguar.partnerservice.service;

import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponseLong;
import org.yaguar.partnerservice.api.dto.response.CountryResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;

import java.util.List;

public interface CountryService {
    public Result<Long> createCountry(CountryAddRequest countryAddRequest);
    public Result<Void>  deleteCountryById(Long id);
    public Result<List<CountryResponseShort>> findAllCountries();
    public Result<CountryResponseLong> findCountryById(Long id);
    public Result<Void> updateCountry(Long id, CountryUpdateRequest countryUpdateRequest);
}
