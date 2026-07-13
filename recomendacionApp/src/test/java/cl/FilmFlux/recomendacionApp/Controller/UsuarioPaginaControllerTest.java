package cl.FilmFlux.recomendacionApp.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import cl.FilmFlux.recomendacionApp.controller.UsuarioController;
import cl.FilmFlux.recomendacionApp.service.UsuarioService;
import cl.FilmFlux.recomendacionApp.model.UsuarioPagina;

@ExtendWith(MockitoExtension.class)
public class UsuarioPaginaControllerTest {
    
    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void crearUsuarioRetorna201(){
        UsuarioPagina usuario = new UsuarioPagina(
            1,
            "Tomas Martinez",
            "tomas.martinez@example.com",
            "password456",
            "Fantasia",
            "Accion"
        );

        
        when(usuarioService.saveUsuario(usuario)).thenReturn(usuario);

        var respuesta = usuarioController.saveUsuario(usuario);

        assertNotNull(respuesta);

        assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());

        var body = respuesta.getBody();
        assertNotNull(body);

        assertEquals("Tomas Martinez", body.getNombre());
    }
}