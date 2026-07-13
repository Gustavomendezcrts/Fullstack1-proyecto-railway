package cl.FilmFlux.recomendacionApp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.FilmFlux.recomendacionApp.model.Resena;
import cl.FilmFlux.recomendacionApp.service.ResenaService;
import cl.FilmFlux.recomendacionApp.DTO.Resena_DTO;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/v1/resenas")
@RestController
public class ResenaController {
    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<List<Resena_DTO>> getResenas(){
        List<Resena_DTO> resenas = resenaService.getResenas();

        if(resenas.isEmpty()){
            System.out.println("[" + LocalDateTime.now() + "] No se encontraron reseñas");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(resenas);
    }

    @PostMapping
    public ResponseEntity<Resena> saveResena(@Valid @RequestBody Resena resena){

        if(resena == null){
            System.out.println("[" + LocalDateTime.now() + "] Datos de reseña no proporcionados");
            return ResponseEntity.badRequest().build();
        }

        Resena nuevaResena = resenaService.saveResena(resena);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaResena);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Resena> getResena(@PathVariable int id){
        Resena resena = resenaService.getResenaId(id);
        if(resena == null){
            System.out.println("[" + LocalDateTime.now() + "] No se encontro la reseña");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(resena);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Resena> actualizarResena(@PathVariable int id, @Valid @RequestBody Resena resena) {
        if(resena == null){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al actualizar reseña: Datos de reseña no proporcionados");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            resena.setIdResena(id);
            Resena resenaActualizada = resenaService.updateResena(resena);
            if (resenaActualizada == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(resenaActualizada);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResena(@PathVariable int id) {
        if(resenaService.getResenaId(id) == null){
             return ResponseEntity.notFound().build();
        }else{
            resenaService.deleteResena(id);
            return ResponseEntity.noContent().build();
        }        
    }

    // Metodos especiales
    @GetMapping("/resenasPorUsuario/{id}")
    public ResponseEntity<List<Resena_DTO>> getResenasByUsuario(@PathVariable int id){
        List<Resena_DTO> resenas = resenaService.getResenasByUsuario(id);
        if(resenas.isEmpty()){
            System.out.println("[" + LocalDateTime.now() + "] No se encontraron reseñas para el usuario ID: " + id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/resenasPorPelicula/{id}")
    public ResponseEntity<List<Resena_DTO>> getResenasByPelicula(@PathVariable int id){
        List<Resena_DTO> resenas = resenaService.getResenasByPelicula(id);
        if(resenas.isEmpty()){
            System.out.println("[" + LocalDateTime.now() + "] No se encontraron reseñas para la película ID: " + id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }

    @GetMapping("/resenasPorSerie/{id}")
    public ResponseEntity<List<Resena_DTO>> getResenasBySerie(@PathVariable int id){
        List<Resena_DTO> resenas = resenaService.getResenasBySerie(id);
        if(resenas.isEmpty()){
            System.out.println("[" + LocalDateTime.now() + "] No se encontraron reseñas para la serie ID: " + id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(resenas);
    }
}
