package org.yaguar.partnerservice.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponseLong;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.mapper.ResultStatusMapper;
import org.yaguar.partnerservice.service.CountryService;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@Validated
class CountryController {
    private final CountryService countryService;
    private final ResultStatusMapper resultStatusMapper;

    @GetMapping
    public ResponseEntity<Result<Page<CountryResponseLong>>> findAllCountries(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                                               @RequestParam(defaultValue = "15") int size) {
        int maxSize = 30;
        size = Math.min(size, maxSize);

        var countryListResult = countryService.findCountries(PageRequest.of(page, size, Sort.by("name").ascending()));
        return new ResponseEntity<>(countryListResult, resultStatusMapper.toHttpStatus(countryListResult.getStatus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<CountryResponseLong>> findCountryById(@PathVariable @Positive Long id) {
        var countryResult = countryService.findCountryById(id);
        return new ResponseEntity<>(countryResult, resultStatusMapper.toHttpStatus(countryResult.getStatus()));
    }

    @PostMapping
    public ResponseEntity<Result<Long>> createCountry(@RequestBody @Valid CountryAddRequest countryAddRequest) {
        var createResult = countryService.createCountry(countryAddRequest);
        return new ResponseEntity<>(createResult, resultStatusMapper.toHttpStatus(createResult.getStatus()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteCountryById(@PathVariable @Positive Long id) {
        var deleteResult = countryService.deleteCountryById(id);
        return new ResponseEntity<>(deleteResult, resultStatusMapper.toHttpStatus(deleteResult.getStatus()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Void>> updateCountryById(@PathVariable @Positive Long id,
                                                          @RequestBody @Valid CountryUpdateRequest countryUpdateRequest) {
        var updateResult = countryService.updateCountry(id, countryUpdateRequest);
        return new ResponseEntity<>(updateResult, resultStatusMapper.toHttpStatus(updateResult.getStatus()));
    }
}
