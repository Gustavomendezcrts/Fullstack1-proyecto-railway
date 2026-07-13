package cl.FilmFlux.recomendacionApp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.FilmFlux.recomendacionApp.model.Pelicula;
import cl.FilmFlux.recomendacionApp.service.PeliculaService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/v1/peliculas")
@RestController
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<List<Pelicula>> getPeliculas(){
        List<Pelicula> peliculas = peliculaService.getPeliculas();

        if(peliculas.isEmpty()){
            System.out.println("[" + LocalDateTime.now() + "] " + "No se encontraron peliculas");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.ok(peliculaService.getPeliculas());
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Pelicula>> getPeliculasByGenero(@PathVariable String genero){
        List<Pelicula> peliculasByGenero = peliculaService.getPeliculasByGenero(genero);

        if(peliculasByGenero.isEmpty()){
            System.out.println("[" + LocalDateTime.now() + "] " + "No se encontraron peliculas para el genero: " + genero);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.ok(peliculaService.getPeliculasByGenero(genero));
        }
    }

    @PostMapping
    public ResponseEntity<Pelicula> savePelicula(@Valid @RequestBody Pelicula pelicula){
        if(pelicula == null){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al guardar pelicula: Datos de pelicula no proporcionados");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.savePelicula(pelicula));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable int id, @Valid @RequestBody Pelicula peli) {
        if(peli == null){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al actualizar pelicula: Datos de pelicula no proporcionados");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            peli.setIdPelicula(id);
            Pelicula peliActualizada = peliculaService.updatePelicula(peli);
            if (peliActualizada == null) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(peliActualizada);
        }
    }
    
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable int id) {

        if(peliculaService.getPeliculaId(id) == null){
             return ResponseEntity.notFound().build();
        }

        peliculaService.deletePelicula(id);

        return ResponseEntity.noContent().build();
    }

}
