package org.yaguar.partnerservice.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaguar.partnerservice.api.dto.request.CompanyAddRequest;
import org.yaguar.partnerservice.api.dto.request.CompanyUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseLong;
import org.yaguar.partnerservice.api.dto.response.CompanyResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.mapper.ResultStatusMapper;
import org.yaguar.partnerservice.service.CompanyService;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
@Validated
class CompanyController {
    private final CompanyService companyService;
    private final ResultStatusMapper resultStatusMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Result<CompanyResponseLong>> findCompanyById(@PathVariable @Positive @NotNull Long id) {
        var result = companyService.findById(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @GetMapping
    public ResponseEntity<Result<Page<CompanyResponseShort>>> findAllCompanies(@RequestParam(defaultValue = "15") @Positive int size,
                                                                               @RequestParam(defaultValue = "0") @PositiveOrZero int page) {
        int maxSize = 30;
        size = Math.min(size, maxSize);

        var result = companyService
                .findAll(PageRequest.of(page, size, Sort.by("name").ascending()));
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PostMapping
    public ResponseEntity<Result<Long>> createCompany(@RequestBody @Valid CompanyAddRequest companyAddRequest) {
        var result = companyService.createCompany(companyAddRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteCompany(@PathVariable @Positive @NotNull Long id) {
        var result = companyService.deleteCompany(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Void>> updateCompany(@PathVariable @Positive @NotNull Long id,
                                                      @RequestBody @Valid CompanyUpdateRequest companyUpdateRequest) {
        var result = companyService.updateCompany(id, companyUpdateRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }
}
