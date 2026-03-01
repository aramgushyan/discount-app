package org.yaguar.partnerservice.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaguar.partnerservice.api.dto.request.CountryAddRequest;
import org.yaguar.partnerservice.api.dto.request.CountryUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CountryResponseLong;
import org.yaguar.partnerservice.api.dto.response.CountryResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
@Validated
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<Result<List<CountryResponseShort>>> findAllCountries() {
        var countryListResult = countryService.findAllCountries();
        return new ResponseEntity<>(countryListResult, countryListResult.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<CountryResponseLong>> findCountryById(@PathVariable @Positive Long id) {
        var countryResult = countryService.findCountryById(id);
        return new ResponseEntity<>(countryResult, countryResult.getStatus());
    }

    @PostMapping
    public ResponseEntity<Result<CountryResponseLong>> createCountry(@RequestBody @Valid CountryAddRequest countryAddRequest) {
        var createResult = countryService.createCountry(countryAddRequest);
        return new ResponseEntity<>(createResult, createResult.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteCountryById(@PathVariable @Positive Long id) {
        var deleteResult = countryService.deleteCountryById(id);
        return new ResponseEntity<>(deleteResult, deleteResult.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Void>> updateCountryById(@PathVariable @Positive Long id,
                                                          @RequestBody @Valid CountryUpdateRequest countryUpdateRequest) {
        var updateResult = countryService.updateCountry(id, countryUpdateRequest);
        return new ResponseEntity<>(updateResult, updateResult.getStatus());
    }
}
