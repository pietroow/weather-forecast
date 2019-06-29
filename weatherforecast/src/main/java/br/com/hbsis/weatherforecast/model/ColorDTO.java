package br.com.hbsis.weatherforecast.model;

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
