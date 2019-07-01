package br.com.hbsis.weatherforecast.exception;

public class CityOpenWeatherNotFound extends RuntimeException {

    public CityOpenWeatherNotFound(Long id) {
        super("Could not find city with id "+id+" in open weather api");
    }

}
