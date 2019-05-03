package com.uvt.bot.dobby.util.exceptions;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.messageType.TextMessage;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<RecastReply> defaultErrorHandler(EntityNotFoundException ex) {
        RecastReply recastReply = new RecastReply(new ArrayList<>());
        TextMessage textMessage = new TextMessage(ex.getMessage());
        recastReply.getReplies().add(textMessage);
        return ResponseEntity.status(HttpStatus.OK).body(recastReply);
    }

    private ResponseStatus getStatus(Exception ex) {
        return AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
    }
}
