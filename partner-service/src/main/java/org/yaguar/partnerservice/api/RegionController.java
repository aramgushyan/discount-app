package org.yaguar.partnerservice.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaguar.partnerservice.api.dto.response.RegionResponseShort;
import org.yaguar.partnerservice.api.dto.response.Result;
import org.yaguar.partnerservice.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/regions")
@RequiredArgsConstructor
@Validated
public class RegionController {
    private final RegionService  regionService;
//
//    @GetMapping
//    public ResponseEntity<Result<List<RegionResponseShort>>> findAllRegionsByCountryId() {
//        var result = new Result<List<RegionResponseShort>>();
//        return ResponseEntity.status(result.getStatus()).body(result);
//    }


}
