package util;

import com.example.catalogfilm.CatalogFilmApplication;
import com.example.catalogfilm.model.Director;
import com.example.catalogfilm.service.DirectorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CatalogFilmApplication.class)
@AutoConfigureMockMvc
public class DirectorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectorService directorService;

    @Test
    public void addDirectorTest_whenMockMVC_thenReturnHttpStatusOK() throws Exception {
        Director director = new Director();
        director.setName("Christofer Nolan");
        when(directorService.saveDirector(any(Director.class))).thenReturn(director);
        mockMvc.perform(MockMvcRequestBuilders.post("/director")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Christofer Nolan\"}")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Christofer Nolan\"}"));
    }

    @Test
    public void getDirectorTest_whenMockMVC_thenReturnsHttpStatusOK() throws Exception {
        UUID directorUuid = UUID.randomUUID();
        Director director = new Director();
        director.setUuid(directorUuid);
        director.setName("Christofer Nolan");

        when(directorService.getDirector(ArgumentMatchers.eq(directorUuid))).thenReturn(director);

        mockMvc.perform(MockMvcRequestBuilders.get("/director")
                        .param("directorUuid", directorUuid.toString())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"uuid\":\"" + directorUuid + "\",\"name\":\"Christofer Nolan\"}"));
    }
}
