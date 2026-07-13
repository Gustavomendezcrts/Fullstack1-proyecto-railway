package cl.FilmFlux.recomendacionApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.FilmFlux.recomendacionApp.model.Serie;
import cl.FilmFlux.recomendacionApp.repository.SerieRepository;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    public List<Serie> getSeries(){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo todas las series: | " + serieRepository.findAll().size() + " Elementos");
            return serieRepository.findAll();
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer series: | " + e.getMessage());
            return null;
        }
    }

    public Serie saveSerie(Serie serie){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Guardando serie: " + serie.getTitulo());
            return serieRepository.save(serie);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al guardar serie: | " + e.getMessage());
            return null;
        }
    }

    public Serie getSerieId(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo serie por ID: " + id);
            return serieRepository.findById(id).orElse(null);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer serie: | " + e.getMessage());
            return null;
        }
    }

    public Serie updateSerie(Serie serie){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Actualizando serie | ID: " + serie.getIdSerie());
            if(!serieRepository.existsById(serie.getIdSerie())){
                return null;
            }
            return serieRepository.save(serie);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al actualizar serie: | " + e.getMessage());
            return null;
        }
    }

    public void deleteSerie(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Borrando serie | ID: " + id);
            serieRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al borrar serie: | " + e.getMessage());
        }
    }
}
