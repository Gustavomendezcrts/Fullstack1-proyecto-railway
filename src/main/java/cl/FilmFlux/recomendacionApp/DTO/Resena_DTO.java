package cl.FilmFlux.recomendacionApp.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena_DTO {
    private Integer puntaje;
    private String comentario;
    // Usuario
    private String nombreUsuario;
    // Tipo de Reseña: Serie o Pelicula
    private String tipo;
    // Serie / Pelicula
    private String titulo;
    private Date fechaEstreno;
    private Integer puntajeMedia; // Valor de Puntaje para Serie/Pelicula
}
