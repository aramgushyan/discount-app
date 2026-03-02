package org.yaguar.partnerservice.service;

import org.yaguar.partnerservice.api.dto.request.CityAddRequest;
import org.yaguar.partnerservice.api.dto.request.CityUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CityResponseLong;
import org.yaguar.partnerservice.api.dto.response.CityResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;

import java.util.List;

public interface CityService {
    Result<CityResponseLong> findCityById(Long id);

    Result<List<CityResponseShort>> findAllCities();

    Result<Long> createCity(CityAddRequest cityAddRequest);

    Result<Void> updateCity(Long id, CityUpdateRequest cityUpdateRequest);

    Result<Void> deleteCityById(Long id);
}
