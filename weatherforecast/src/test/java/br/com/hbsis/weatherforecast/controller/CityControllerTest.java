package br.com.hbsis.weatherforecast.controller;

import br.com.hbsis.weatherforecast.AbstractControllerTest;
import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.service.CityService;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityControllerTest extends AbstractControllerTest {

    @Mock
    private CityService cityServiceMock;
    private CityController cityController;
    private CityOpenWeather cityOpenWeather;
    private City city;

    private String uri = "/city";

    @Override
    public void set_up() {
        super.set_up();
        this.cityController = new CityController(cityServiceMock);
        this.registerController(this.cityController);

        this.cityOpenWeather = new CityOpenWeather(1850147L, "Tokyo", "JP");
        this.city = new City(UUID.fromString("3cca6e27-5317-49d2-9096-23435e1b5e60"), cityOpenWeather);
    }

    @Test
    public void findByName() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void registerCity() throws Exception {
        String payload = new JSONObject()
//                .put("cityd", 1850147)
                .toString();

        ArgumentCaptor<CityForm> cityArgumentCaptor = ArgumentCaptor.forClass(CityForm.class);
        when(cityServiceMock.registerCity(cityArgumentCaptor.capture())).thenReturn(this.city);

        mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(payload))
                .andExpect(status().isCreated());
    }

    @Test
    public void consumeOpenWeatherApi() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void deleteCityById() {
    }
}