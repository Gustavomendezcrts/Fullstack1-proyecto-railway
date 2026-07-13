package cl.FilmFlux.recomendacionApp.repository;

import java.util.List;

import cl.FilmFlux.recomendacionApp.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Integer>{
    List<Resena> findByUsuario_idUsuario(Integer idUsuario);

    List<Resena> findByPelicula_idPelicula(Integer idPelicula);

    List<Resena> findBySerie_idSerie(Integer idSerie);
}
