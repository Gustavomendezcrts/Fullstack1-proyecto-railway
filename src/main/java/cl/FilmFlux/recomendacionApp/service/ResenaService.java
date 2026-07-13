package cl.FilmFlux.recomendacionApp.service;

import java.util.List;
import java.util.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.FilmFlux.recomendacionApp.DTO.Resena_DTO;
import cl.FilmFlux.recomendacionApp.model.Resena;
import cl.FilmFlux.recomendacionApp.repository.ResenaRepository;

@Service
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena_DTO> getResenas(){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo todas las reseñas | " + resenaRepository.findAll().size() + " Elementos");
            return resenaRepository.findAll().stream()
            .map(r -> {
                    String tipo;
                    String titulo;
                    Date fechaEstreno;
                    Integer puntajeMedia;

                    if (r.getPelicula() != null) {

                        tipo = "Pelicula";
                        titulo = r.getPelicula().getTitulo();
                        fechaEstreno = r.getPelicula().getFechaEstreno();
                        puntajeMedia = r.getPelicula().getPuntaje();
                    
                    System.out.println("[" + LocalDateTime.now() + "]" + " Trayendo Reseña de: " + r.getUsuario().getNombre() + " | Para Pelicula: " + titulo);
                    } else {

                        tipo = "Serie";
                        titulo = r.getSerie().getTitulo();
                        fechaEstreno = r.getSerie().getFechaEstreno();
                        puntajeMedia = r.getSerie().getPuntaje();
                    
                    System.out.println("[" + LocalDateTime.now() + "]" + " Trayendo Reseña de: " + r.getUsuario().getNombre() + " | Para Serie: " + titulo);
                    }

                    return new Resena_DTO(
                            r.getPuntaje(),
                            r.getComentario(),
                            r.getUsuario().getNombre(),
                            tipo,
                            titulo,
                            fechaEstreno,
                            puntajeMedia
                    );
            }).toList();
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer reseñas: | " + e.getMessage());
            return null;
        }
    }

    public Resena saveResena(Resena resena){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Guardando reseña | Usuario: " + resena.getUsuario().getNombre());
            return resenaRepository.save(resena);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al guardar reseña: | " + e.getMessage());
            return null;
        }
    }

    public Resena getResenaId(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo reseña | Id: " + id);
            return resenaRepository.findById(id).orElse(null);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer reseña: | " + e.getMessage());
            return null;
        }
    }

    public Resena updateResena(Resena resena){
        try{
            if(!resenaRepository.existsById(resena.getIdResena())){
                System.out.println("[" + LocalDateTime.now() + "] " + "Reseña no encontrada");
                return null;
            }
            System.out.println("[" + LocalDateTime.now() + "] " + "Actualizando reseña Id: \n" + resena.getIdResena());
            return resenaRepository.save(resena);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al actualizar reseña: | " + e.getMessage());
            return null;
        }
    }

    public void deleteResena(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Eliminando reseña | Id: " + id);
            resenaRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al eliminar reseña: | " + e.getMessage());
        }
    }

    public List<Resena_DTO> getResenasByUsuario(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo reseñas por Usuario | " + resenaRepository.findByUsuario_idUsuario(id).size() + " Elementos");
            return resenaRepository.findByUsuario_idUsuario(id).stream()
            .map(r -> {
                String tipo;
                String titulo;
                Date fechaEstreno;
                Integer puntajeMedia;

                if (r.getPelicula() != null) {

                    tipo = "Pelicula";
                    titulo = r.getPelicula().getTitulo();
                    fechaEstreno = r.getPelicula().getFechaEstreno();
                    puntajeMedia = r.getPelicula().getPuntaje();
                
                System.out.println("[" + LocalDateTime.now() + "]" + " Trayendo Reseña por ID Usuario: " + id + " | Para Pelicula: " + titulo);
                } else {

                    tipo = "Serie";
                    titulo = r.getSerie().getTitulo();
                    fechaEstreno = r.getSerie().getFechaEstreno();
                    puntajeMedia = r.getSerie().getPuntaje();
                
                System.out.println("[" + LocalDateTime.now() + "]" + " Trayendo Reseña por ID Usuario: " + id + " | Para Serie: " + titulo);
                }

                return new Resena_DTO(
                        r.getPuntaje(),
                        r.getComentario(),
                        r.getUsuario().getNombre(),
                        tipo,
                        titulo,
                        fechaEstreno,
                        puntajeMedia
                );
            })
            .toList();
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer reseñas: | " + e.getMessage());
            return null;
        }
    }

    public List<Resena_DTO> getResenasByPelicula(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo reseñas por ID Pelicula | " + resenaRepository.findByPelicula_idPelicula(id).size() + " Elementos");
            return resenaRepository.findByPelicula_idPelicula(id).stream()
            .map(r -> {
                System.out.println("[" + LocalDateTime.now() + "]" + " Trayendo Reseña por ID Pelicula: " + id + " | Titulo: " + r.getPelicula().getTitulo());

            return new Resena_DTO(
            r.getPuntaje(),
            r.getComentario(),
            r.getUsuario().getNombre(),
            "Pelicula",
            r.getPelicula().getTitulo(),
            r.getPelicula().getFechaEstreno(),
            r.getPelicula().getPuntaje()
            );
        }).toList();
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer reseñas: | " + e.getMessage());
            return null;
        }
    }

    public List<Resena_DTO> getResenasBySerie(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo reseñas por ID Serie | " + resenaRepository.findBySerie_idSerie(id).size() + " Elementos");
            return resenaRepository.findBySerie_idSerie(id).stream()
            .map(r -> {
                System.out.println("[" + LocalDateTime.now() + "]" + " Trayendo Reseña por ID Serie: " + id + " | Titulo: " + r.getSerie().getTitulo());
                
                return new Resena_DTO(
                r.getPuntaje(),
                r.getComentario(),
                r.getUsuario().getNombre(),
                "Serie",
                r.getSerie().getTitulo(),
                r.getSerie().getFechaEstreno(),
                r.getSerie().getPuntaje()
                );
            }).toList();
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer reseñas: | " + e.getMessage());
            return null;
        }
    }
}
