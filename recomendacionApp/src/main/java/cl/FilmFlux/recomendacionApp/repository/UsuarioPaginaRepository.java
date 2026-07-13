package cl.FilmFlux.recomendacionApp.repository;

import cl.FilmFlux.recomendacionApp.model.UsuarioPagina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPaginaRepository extends JpaRepository<UsuarioPagina, Integer>{
    UsuarioPagina findByNombre(String nombre);
}
