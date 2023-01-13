package ru.perm.v.parking.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class Error502 extends RuntimeException {
    public Error502(String message) {
        super(message);
        log.error(message);
    }
}
