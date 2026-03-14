package org.yaguar.partnerservice.api.dto.response;

import org.yaguar.partnerservice.entity.Status;

public record CompanyResponseShort(Long id, String name, Long ownerId) {
}
