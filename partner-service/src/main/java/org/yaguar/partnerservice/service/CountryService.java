package org.yaguar.partnerservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponseLong;
import org.yaguar.partnerservice.api.dto.response.Result;

public interface CountryService {
    public Result<Long> createCountry(CountryAddRequest countryAddRequest);

    public Result<Void> deleteCountryById(Long id);

    public Result<Page<CountryResponseLong>> findCountries(Pageable pageable);

    public Result<CountryResponseLong> findCountryById(Long id);

    public Result<Void> updateCountry(Long id, CountryUpdateRequest countryUpdateRequest);
}
