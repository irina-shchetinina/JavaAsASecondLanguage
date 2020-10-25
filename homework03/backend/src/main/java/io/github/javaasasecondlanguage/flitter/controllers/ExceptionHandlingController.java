package io.github.javaasasecondlanguage.flitter.controllers;

import io.github.javaasasecondlanguage.flitter.controllers.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleError(HttpServletRequest req, Exception ex) {
        var res = new ResponseDTO<Object>(ex.getMessage());
        System.out.println(res.getErrorMessage());
        return ResponseEntity.badRequest().body(res);
    }

}
