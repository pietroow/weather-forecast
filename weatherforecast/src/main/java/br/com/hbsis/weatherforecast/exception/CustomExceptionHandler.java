package br.com.hbsis.weatherforecast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CityOpenWeatherNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cityOpenWeatherNotFoundHandler(CityOpenWeatherNotFound ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String HttpClientErrorException$NotFound(HttpClientErrorException ex){
        return ex.getMessage();
    }
}
