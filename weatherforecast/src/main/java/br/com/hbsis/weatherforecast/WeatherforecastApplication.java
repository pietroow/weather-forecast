package br.com.hbsis.weatherforecast;

import br.com.hbsis.weatherforecast.model.CityOpenWeather;
import br.com.hbsis.weatherforecast.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WeatherforecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherforecastApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CityService cityService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            List<CityOpenWeather> cities = cityService.findAll();
            List<CityOpenWeather> cityOpenWeathers = Arrays.asList(mapper.readValue(new File(getFullPath()), CityOpenWeather[].class));
            if (cities.size() != cityOpenWeathers.size()) {
                cityService.saveAll(cityOpenWeathers);
            }
        };
    }

    private String getFullPath() {
        String userDir = System.getProperty("user.dir");
        String path = "\\src\\main\\resources\\file\\";
        String fileName = "city.list.json";
        return userDir + path + fileName;
    }
}
