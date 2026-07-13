package cl.FilmFlux.recomendacionApp.repository;

import java.util.List;

import cl.FilmFlux.recomendacionApp.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer>{  
    List<Pelicula> findByGenero(String genero);
}
