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

import cl.FilmFlux.recomendacionApp.controller.ResenaController;
import cl.FilmFlux.recomendacionApp.model.Resena;
import cl.FilmFlux.recomendacionApp.model.Serie;
import cl.FilmFlux.recomendacionApp.model.UsuarioPagina;
import cl.FilmFlux.recomendacionApp.service.ResenaService;

@ExtendWith(MockitoExtension.class)
public class ResenaControllerTest {

    @Mock
    private ResenaService resenaService;

    @InjectMocks
    private ResenaController resenaController;

    @Test
    void crearResenaRetorna201(){
        Serie serie = new Serie(
            1,
            "tt0108778",
            "Friends",
            "David Crane",
            Date.valueOf("1994-09-22"),
            "Comedia",
            9,
            10
        );
        UsuarioPagina usuario = new UsuarioPagina(
            1,
            "Gustavo",
            "gu.mendez@test.com",
            "TEST",
            "Comedia",
            "Anime"
        );

        Resena resena = new Resena(
            1,
            8,
            "Excelente Serie, la recomiendo mucho",
            usuario,
            null,
            serie
        );

        when(resenaService.saveResena(resena)).thenReturn(resena);

        var respuesta = resenaController.saveResena(resena);

        assertNotNull(respuesta);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals(8, body.getPuntaje());
        assertEquals("Excelente Serie, la recomiendo mucho", body.getComentario());
        assertEquals("Gustavo", body.getUsuario().getNombre());
        assertEquals("Friends", body.getSerie().getTitulo());
    }
}
