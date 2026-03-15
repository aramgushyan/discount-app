package org.yaguar.partnerservice.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaguar.partnerservice.api.dto.request.CityAddRequest;
import org.yaguar.partnerservice.api.dto.request.CityUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CityResponseLong;
import org.yaguar.partnerservice.api.dto.response.CityResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.mapper.ResultStatusMapper;
import org.yaguar.partnerservice.service.CityService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
@Validated
class CityController {
    private final CityService cityService;
    private final ResultStatusMapper resultStatusMapper;

    @GetMapping
    public ResponseEntity<Result<List<CityResponseShort>>> findAllCities() {
        var result = cityService.findAllCities();
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<CityResponseLong>> findById(@PathVariable @Positive Long id) {
        var result = cityService.findCityById(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PostMapping
    public ResponseEntity<Result<Long>> createCity(@RequestBody @Valid CityAddRequest cityAddRequest) {
        var result = cityService.createCity(cityAddRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteCity(@PathVariable @Positive Long id) {
        var result = cityService.deleteCityById(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Void>> updateCity(@PathVariable @Positive Long id,
                                                   @RequestBody @Valid CityUpdateRequest cityUpdateRequest) {
        var result = cityService.updateCity(id, cityUpdateRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }
}
