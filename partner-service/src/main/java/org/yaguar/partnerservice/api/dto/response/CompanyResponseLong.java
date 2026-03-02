package org.yaguar.partnerservice.api.dto.response;

import java.util.List;

public record CompanyResponseLong(Long id, String name, Long ownerId,List<UnitResponse> unitResponse) {
}
