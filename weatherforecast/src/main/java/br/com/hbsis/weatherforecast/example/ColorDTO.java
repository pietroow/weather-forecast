package br.com.hbsis.weatherforecast.example;

import br.com.hbsis.weatherforecast.example.Color;
import org.springframework.beans.BeanUtils;

public class ColorDTO {

    private String name;

    public String getName() {
        return name;
    }

    public Color convertToEntity(){
        Color color = new Color();
        BeanUtils.copyProperties(this, color);
        return color;
    }
}
