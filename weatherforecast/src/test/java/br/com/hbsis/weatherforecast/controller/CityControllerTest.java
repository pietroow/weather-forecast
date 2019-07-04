package br.com.hbsis.weatherforecast.controller;

import br.com.hbsis.weatherforecast.AbstractControllerTest;
import br.com.hbsis.weatherforecast.model.City;
import br.com.hbsis.weatherforecast.model.CityOpenWeather;
import br.com.hbsis.weatherforecast.model.dto.CityForm;
import br.com.hbsis.weatherforecast.model.dto.response.custom.CustomResponse;
import br.com.hbsis.weatherforecast.model.dto.response.custom.DayForecast;
import br.com.hbsis.weatherforecast.model.dto.response.openweatherapi.CityResponseDTO;
import br.com.hbsis.weatherforecast.model.dto.response.openweatherapi.WeatherMainDTO;
import br.com.hbsis.weatherforecast.model.dto.response.openweatherapi.WeatherResponseDTO;
import br.com.hbsis.weatherforecast.service.CityService;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CityControllerTest extends AbstractControllerTest {

    @Mock
    private CityService cityServiceMock;

    @Mock
    private RestTemplate restTemplate;

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
        this.city = new City(UUID.fromString("3cca6e27-5317-49d2-9096-23435e1b5e60"), this.cityOpenWeather);
    }

    @Test
    public void registerCityReturning201Created() throws Exception {
        String payload = new JSONObject()
                .put("cityId", 1850147)
                .toString();

        ArgumentCaptor<CityForm> argumentCaptor = ArgumentCaptor.forClass(CityForm.class);
        when(cityServiceMock.registerCity(argumentCaptor.capture())).thenReturn(this.city);

        mockMvc.perform(post(uri)
                .contentType(contentType)
                .content(payload))
                .andExpect(status().isCreated());
    }

    @Test
    public void findAllCitiesRegisteredLocal() throws Exception {
        String uri = this.uri + "/local";
        when(cityServiceMock.findAllLocal()).thenReturn(Collections.singletonList(this.city));

        mockMvc.perform(get(uri))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteSuccessReturning204NoContent() throws Exception {
        String uri = this.uri + "/026491b3-1013-433c-8971-4a3913762e7e";

        mockMvc.perform(delete(uri))
                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteFailReturning400BadRequest() throws Exception {
        String uri = this.uri + "/433c-8971-4a3913762e7e";

        mockMvc.perform(delete(uri))
                .andExpect(status().isBadRequest());
    }
}