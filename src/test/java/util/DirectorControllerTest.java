package util;

import com.example.catalogfilm.CatalogFilmApplication;
import com.example.catalogfilm.model.Director;
import com.example.catalogfilm.service.DirectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = CatalogFilmApplication.class)
@AutoConfigureMockMvc
public class DirectorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DirectorService directorService;

    @Test
    public void getDirectorTest_whenMockMVC_thenReturnsHttpStatusOK() throws Exception {
        mockMvc.perform(get("/director?directorUuid=4b60f5e7-5ec4-4d20-bf63-bd060f8097f9")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addDirectorTest_whenMockMVC_thenReturnHttpStatusOK() throws Exception {
        Director director = new Director(UUID.fromString("4b60f5e7-5ec4-4d20-bf63-bd060f8097f9"), "Kirill", 21, "Russia", null);
        mockMvc.perform(post("/director")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(director)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Director director) {
        try {
            return new ObjectMapper().writeValueAsString(director);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
