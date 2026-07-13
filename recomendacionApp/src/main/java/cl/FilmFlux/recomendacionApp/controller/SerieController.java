package cl.FilmFlux.recomendacionApp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.FilmFlux.recomendacionApp.model.Serie;
import cl.FilmFlux.recomendacionApp.service.SerieService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/v1/series")
@RestController
public class SerieController {
    @Autowired
    private SerieService serieService;

    @GetMapping
    public ResponseEntity<List<Serie>> getSeries(){
        List<Serie> series = serieService.getSeries();

        if(series.isEmpty()){
            System.out.println("[" + LocalDateTime.now() + "] " + "No se encontraron series");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.ok(serieService.getSeries());
        }
    }

    @PostMapping
    public ResponseEntity<Serie> saveSerie(@Valid @RequestBody Serie serie){
        if(serie == null){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al guardar serie: Datos de serie no proporcionados");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(serieService.saveSerie(serie));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Serie> actualizarSerie(@PathVariable int id, @Valid @RequestBody Serie serie) {
        if(serie == null){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al actualizar serie: Datos de serie no proporcionados");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            serie.setIdSerie(id);
            Serie serieActualizada = serieService.updateSerie(serie);
            if (serieActualizada == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(serieActualizada);
        }
    }
    
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSerie(@PathVariable int id) {

        if(serieService.getSerieId(id) == null){
             return ResponseEntity.notFound().build();
        }

        serieService.deleteSerie(id);

        return ResponseEntity.noContent().build();
    }

}
