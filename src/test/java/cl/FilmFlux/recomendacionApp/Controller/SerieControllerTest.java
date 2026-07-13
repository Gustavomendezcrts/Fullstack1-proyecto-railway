package cl.FilmFlux.recomendacionApp.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import cl.FilmFlux.recomendacionApp.controller.SerieController;
import cl.FilmFlux.recomendacionApp.service.SerieService;
import cl.FilmFlux.recomendacionApp.model.Serie;

@ExtendWith(MockitoExtension.class)
public class SerieControllerTest {
    
    @Mock
    private SerieService serieService;

    @InjectMocks
    private SerieController serieController;

    @Test
    void crearSerieRetorna201(){
        Serie serie = new Serie(
            1,
            "tt12324366",
            "Percy Jackson y los dioses del Olimpo",
            "Rick Riordan",
            Date.valueOf("2023-12-19"),
            "Fantasia",
            7,
            2
        );

        when(serieService.saveSerie(serie)).thenReturn(serie);

        var respuesta = serieController.saveSerie(serie);

        assertNotNull(respuesta);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals("Percy Jackson y los dioses del Olimpo", body.getTitulo());
    }

}
