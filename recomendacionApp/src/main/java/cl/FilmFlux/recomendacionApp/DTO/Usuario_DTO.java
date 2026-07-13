package cl.FilmFlux.recomendacionApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario_DTO {
    private String nombreUsuario;
    private String gustoPrincipal;
    private String gustoSecundario;
}
