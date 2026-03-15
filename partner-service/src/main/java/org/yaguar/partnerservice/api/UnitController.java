package org.yaguar.partnerservice.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaguar.partnerservice.api.dto.request.UnitAddRequest;
import org.yaguar.partnerservice.api.dto.request.UnitUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.api.dto.response.UnitResponse;
import org.yaguar.partnerservice.mapper.ResultStatusMapper;
import org.yaguar.partnerservice.service.UnitService;

@RestController
@RequestMapping("/api/v1/units")
@RequiredArgsConstructor
@Validated
class UnitController {
    private final UnitService unitService;
    private final ResultStatusMapper resultStatusMapper;

    @GetMapping
    public ResponseEntity<Result<Page<UnitResponse>>> findAllUnits(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                                                   @RequestParam(defaultValue = "10") @Positive int size) {
        int maxSize = 40;
        size = Math.min(maxSize, size);

        var result = unitService.findAll(PageRequest.of(page, size));
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result<UnitResponse>> findUnitById(@PathVariable @Positive Long id) {
        var result = unitService.findById(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PostMapping
    public ResponseEntity<Result<Long>> createUnit(@RequestBody @Valid UnitAddRequest unitAddRequest) {
        var result = unitService.createUnit(unitAddRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void>> deleteUnitById(@PathVariable @Positive Long id) {
        var result = unitService.deleteById(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<Void>> updateUnitById(@PathVariable @Positive Long id,
                                                       @RequestBody @Valid UnitUpdateRequest unitUpdateRequest) {
        var result = unitService.updateById(id, unitUpdateRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }
}
