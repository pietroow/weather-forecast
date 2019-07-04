package br.com.hbsis.weatherforecast;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public abstract class AbstractControllerTest {

    protected MockMvc mockMvc;

    protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void set_up() {
        MockitoAnnotations.initMocks(this);
    }

    protected void registerController(Object controller) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }


}
