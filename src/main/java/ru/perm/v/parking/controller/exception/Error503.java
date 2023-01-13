package ru.perm.v.parking.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class Error503 extends RuntimeException {
    public Error503(String message) {
        super(message);
        log.error(message);
    }
}
