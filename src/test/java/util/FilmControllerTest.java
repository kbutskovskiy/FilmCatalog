package util;

import com.example.catalogfilm.CatalogFilmApplication;
import com.example.catalogfilm.constants.GenreEnum;
import com.example.catalogfilm.model.Film;
import com.example.catalogfilm.service.FilmService;
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
public class FilmControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @Test
    public void getFilmTest_whenMockMVC_thenReturnsHttpStatusOK() throws Exception {
        mockMvc.perform(get("/film?filmUuid=4b60f5e7-5ec4-4d20-bf63-bd060f8097f9")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addFilmTest_whenMockMVC_thenReturnHttpStatusOK() throws Exception {
        Film Film = new Film(UUID.fromString("4b60f5e7-5ec4-4d20-bf63-bd060f8097f9"), "KillTheBill", GenreEnum.HORROR, 8);
        mockMvc.perform(post("/film")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(Film)))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Film Film) {
        try {
            return new ObjectMapper().writeValueAsString(Film);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
