package cl.FilmFlux.recomendacionApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resenas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer idResena;

    @NotNull
    private Integer puntaje;

    @NotBlank
    private String comentario;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    private UsuarioPagina usuario;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idPelicula")
    private Pelicula pelicula;

    @ManyToOne(optional = true)
    @JoinColumn(name = "idSerie")
    private Serie serie;
}