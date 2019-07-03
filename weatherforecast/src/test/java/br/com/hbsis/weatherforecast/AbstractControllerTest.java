package br.com.hbsis.weatherforecast;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public abstract class AbstractControllerTest {

    protected MockMvc mockMvc;

    @Before
    public void set_up() {
        MockitoAnnotations.initMocks(this);
    }

    protected void registerController(Object controller) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
    }
}
