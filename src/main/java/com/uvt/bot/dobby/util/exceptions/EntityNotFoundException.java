package com.uvt.bot.dobby.util.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid id")
public class EntityNotFoundException extends RuntimeException {

    private String message;

    private EntityNotFoundException(String message){
        super();
        this.message = message;
    }

    public static EntityNotFoundException withMessage(String message) {

        return new EntityNotFoundException(message);
    }
}
