package org.yaguar.partnerservice.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaguar.partnerservice.api.dto.request.RegionAddRequest;
import org.yaguar.partnerservice.api.dto.request.RegionUpdateRequest;
import org.yaguar.partnerservice.api.dto.response.RegionResponseLong;
import org.yaguar.partnerservice.api.dto.response.RegionResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.mapper.ResultStatusMapper;
import org.yaguar.partnerservice.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
@RequiredArgsConstructor
@Validated
class RegionController {
    private final RegionService  regionService;
    private final ResultStatusMapper resultStatusMapper;

    @GetMapping
    public ResponseEntity<Result<List<RegionResponseShort>>> findAllRegionsByCountryId() {
        var result = regionService.findAllRegions();
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<Result<RegionResponseLong>> findRegionById(@PathVariable @Positive Long id) {
        var result = regionService.findRegionById(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PostMapping
    public ResponseEntity<Result<Long>> createRegion(@RequestBody @Valid RegionAddRequest regionAddRequest) {
        var result = regionService.createRegion(regionAddRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result<Void>> deleteRegionById(@PathVariable @Positive Long id) {
        var result = regionService.deleteRegionById(id);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Result<Void>> updateRegionById(@PathVariable @Positive Long id,
                                                         @RequestBody @Valid RegionUpdateRequest regionUpdateRequest) {
        var result = regionService.updateRegion(id, regionUpdateRequest);
        return ResponseEntity.status(resultStatusMapper.toHttpStatus(result.getStatus())).body(result);
    }

}
