package br.com.hbsis.weatherforecast;

import br.com.hbsis.weatherforecast.model.dto.CityOpenWeather;
import br.com.hbsis.weatherforecast.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class WeatherforecastApplication {

    @Autowired
    private CityService cityService;

    public static void main(String[] args) {
        SpringApplication.run(WeatherforecastApplication.class, args);
    }

    @Order
    @Bean
    CommandLineRunner commandLineRunner(CityService cityService) {
        return args -> {
            String userDir = System.getProperty("user.dir");
            String path = "\\src\\main\\resources\\file\\";
            String fileName = "city.list.json";
            String fullPath = userDir + path + fileName;
            ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(AUTO_CLOSE_TARGET, true);
            List<CityOpenWeather> cityOpenWeathers = Arrays.asList(mapper.readValue(new File(fullPath), CityOpenWeather[].class));
            cityService.saveAll(cityOpenWeathers);
        };
    }

//    @Override
//    public void run(String... args) throws Exception {
//        String userDir = System.getProperty("user.dir");
//        String path = "\\src\\main\\resources\\file\\";
//        String fileName = "city.list.json";
//        String fullPath = userDir + path + fileName;
//        ObjectMapper mapper = new ObjectMapper();
////			mapper.configure(AUTO_CLOSE_TARGET, true);
//        List<CityOpenWeather> cityOpenWeathers = Arrays.asList(mapper.readValue(new File(fullPath), CityOpenWeather[].class));
//        cityService.saveAll(cityOpenWeathers);
//    }
}
