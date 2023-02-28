package util;

import com.example.catalogfilm.model.Director;
import com.example.catalogfilm.repository.DirectorRepository;
import com.example.catalogfilm.service.impl.DirectorServiceImpl;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(/*classes = TestConfiguration.class*/)
@WebAppConfiguration(value = "")
public class DirectorControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = wac.getServletContext();
        Assertions.assertNotNull(servletContext);
        Assertions.assertTrue(servletContext instanceof MockServletContext);
    }

    @MockBean
    private DirectorServiceImpl directorService;

    @MockBean
    private DirectorRepository directorRepository;

    @Test
    public void getDirectorTest_whenMockMVC_thenReturnsHttpStatusOK() throws Exception {
        when(directorService
                .getDirector(UUID.fromString("4b60f5e7-5ec4-4d20-bf63-bd060f8097f9")))
                .thenReturn(new Director());
        when(directorRepository
                .findById(UUID.fromString("4b60f5e7-5ec4-4d20-bf63-bd060f8097f9")))
                .thenReturn(Optional.of(new Director()));
        this.mvc.perform(get("/directorUuid")).andDo(print())
                .andExpect(status().isOk());
    }
}
