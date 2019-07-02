package com.app.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    private final String clazz;
    private final String id;

    public EntityNotFoundException(String clazz, String id) {
        this.clazz = clazz;
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public String getId() {
        return id;
    }
}
