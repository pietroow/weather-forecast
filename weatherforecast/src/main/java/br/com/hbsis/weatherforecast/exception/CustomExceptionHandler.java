package br.com.hbsis.weatherforecast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ColorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String colorNotFoundHandler(ColorNotFoundException ex){
        return ex.getMessage();
    }
}
