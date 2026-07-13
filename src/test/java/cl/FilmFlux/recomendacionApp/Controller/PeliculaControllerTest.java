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

import cl.FilmFlux.recomendacionApp.controller.PeliculaController;
import cl.FilmFlux.recomendacionApp.model.Pelicula;
import cl.FilmFlux.recomendacionApp.service.PeliculaService;

@ExtendWith(MockitoExtension.class)
public class PeliculaControllerTest {

    @Mock
    private PeliculaService peliculaService;

    @InjectMocks
    private PeliculaController peliculaController;

    @Test
    void crearPeliculaRetorna201(){
        Pelicula pelicula = new Pelicula(
        1,
        "tt16183464",
        "One Piece Film: Red",
        "Gorō Taniguchi",
        Date.valueOf("2022-08-06"),
        "Anime",
        7
        );

        when(peliculaService.savePelicula(pelicula)).thenReturn(pelicula);

        var respuesta = peliculaController.savePelicula(pelicula);

        assertNotNull(respuesta);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals("One Piece Film: Red", body.getTitulo());
    }   


}
