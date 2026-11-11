package com.tecsup.exception;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<Map<String,String>> notFound(ResourceNotFoundException ex) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje",ex.getMessage())); }
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,String>> invalid(MethodArgumentNotValidException ex) {
        var errors=ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(e->e.getField(),e->e.getDefaultMessage(),(a,b)->a));
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,String>> badState(IllegalArgumentException ex) { return ResponseEntity.badRequest().body(Map.of("mensaje",ex.getMessage())); }
}
