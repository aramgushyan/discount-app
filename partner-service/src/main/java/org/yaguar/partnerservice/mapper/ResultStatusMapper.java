package org.yaguar.partnerservice.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.yaguar.partnerservice.api.dto.response.ResultStatus;

@Component
public class ResultStatusMapper {
    public HttpStatus toHttpStatus(ResultStatus resultStatus) {
        return switch (resultStatus) {
            case SUCCESS -> HttpStatus.OK;
            case NO_CONTENT -> HttpStatus.NO_CONTENT;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CREATED -> HttpStatus.CREATED;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
