package org.yaguar.partnerservice.api.dto.response;

public record UnitResponse(Long id, Long adminId, String phone,
                           String email, CityResponseShort cityResponseShort,
                           String adress) {
}
