package br.com.hbsis.weatherforecast.exception;

import java.util.UUID;

public class ColorNotFoundException extends RuntimeException {

    public ColorNotFoundException(UUID id) {
        super("Could not find color " + id);
    }

}
