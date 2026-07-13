package cl.FilmFlux.recomendacionApp.service;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.FilmFlux.recomendacionApp.model.Pelicula;
import cl.FilmFlux.recomendacionApp.repository.PeliculaRepository;

@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getPeliculas(){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo todas las peliculas | " + peliculaRepository.findAll().size() + " | Elementos");
            return peliculaRepository.findAll();
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer peliculas: | " + e.getMessage());
            return null;
        }
    }

    public List<Pelicula> getPeliculasByGenero(String genero){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo peliculas por genero: " + genero + " | " + peliculaRepository.findByGenero(genero).size() + " Elementos");
            return peliculaRepository.findByGenero(genero);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer peliculas por genero: " + genero + " | " + e.getMessage());
            return null;
        }
    }

    public Pelicula savePelicula(Pelicula peli){
        try{
        System.out.println("[" + LocalDateTime.now() + "] " + "Guardando pelicula: " + peli.getTitulo());
        return peliculaRepository.save(peli);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al guardar pelicula: " + peli.getTitulo() + " | " + e.getMessage());
            return null;
        }
    }

    public Pelicula getPeliculaId(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo pelicula por ID: " + id);
            return peliculaRepository.findById(id).orElse(null);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer pelicula por ID: " + id + " | " + e.getMessage());
            return null;
        }
    }

    public Pelicula updatePelicula(Pelicula peli){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Actualizando pelicula | ID: " + peli.getIdPelicula());
            if(!peliculaRepository.existsById(peli.getIdPelicula())){
                return null;
            }
            return peliculaRepository.save(peli);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al actualizar pelicula: " + peli.getTitulo() + " | " + e.getMessage());
            return null;
        }
    }

    public void deletePelicula(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Borrando Pelicula | ID: " + id);
            peliculaRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al borrar pelicula: " + id + " | " + e.getMessage());
        }
    }
}
