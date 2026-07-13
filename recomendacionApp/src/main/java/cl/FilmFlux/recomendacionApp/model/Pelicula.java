package cl.FilmFlux.recomendacionApp.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer idPelicula; 
    @NotBlank
    private String imdb;
    @NotBlank
    private String titulo;
    @NotBlank
    private String director;
    @NotNull
    private Date fechaEstreno;
    @NotBlank
    private String genero;
    @NotNull
    private Integer puntaje;
}
