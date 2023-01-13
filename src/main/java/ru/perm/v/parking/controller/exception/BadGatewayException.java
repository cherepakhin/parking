package ru.perm.v.parking.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class BadGatewayException  extends RuntimeException {
    public BadGatewayException(String message) {
        super(message);
        log.error(message);
    }
}
