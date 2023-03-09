package util;

import com.example.catalogfilm.CatalogFilmApplication;
import com.example.catalogfilm.model.Film;
import com.example.catalogfilm.service.FilmService;
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
public class FilmControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService FilmService;

    @Test
    public void addFilmTest_whenMockMVC_thenReturnHttpStatusOK() throws Exception {
        Film film = new Film();
        film.setTitle("TENET");
        when(FilmService.saveFilm(any(Film.class))).thenReturn(film);
        mockMvc.perform(MockMvcRequestBuilders.post("/film")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"TENET\"}")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\":\"TENET\"}"));
    }

    @Test
    public void getFilmTest_whenMockMVC_thenReturnsHttpStatusOK() throws Exception {
        UUID filmUuid = UUID.randomUUID();
        Film film = new Film();
        film.setUuid(filmUuid);
        film.setTitle("TENET");

        when(FilmService.getFilm(ArgumentMatchers.eq(filmUuid))).thenReturn(film);

        mockMvc.perform(MockMvcRequestBuilders.get("/film")
                        .param("filmUuid", filmUuid.toString())).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"uuid\":\"" + filmUuid + "\",\"title\":\"TENET\"}"));
    }
}
