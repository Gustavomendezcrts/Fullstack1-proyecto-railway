package cl.FilmFlux.recomendacionApp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.FilmFlux.recomendacionApp.repository.UsuarioPaginaRepository;
import cl.FilmFlux.recomendacionApp.model.UsuarioPagina;
import cl.FilmFlux.recomendacionApp.DTO.Usuario_DTO;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioPaginaRepository usuarioRepository;

    public List<Usuario_DTO> getUsuarios(){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo todos los usuarios | " + usuarioRepository.findAll().size() + " Elementos");
                return usuarioRepository.findAll().stream()
                .map(u -> new Usuario_DTO(
                    u.getNombre(),
                    u.getGustoPrincipal(),
                    u.getGustoSecundario()
                )).toList();
    
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer usuarios: | " + e.getMessage());
            return null;
        }
    }
    
    public Usuario_DTO getUsuarioById(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo a usuario | ID: " + id);
            UsuarioPagina user = usuarioRepository.findById(id).orElse(null);
            return new Usuario_DTO(
                user.getNombre(),
                user.getGustoPrincipal(),
                user.getGustoSecundario()
            );
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer usuario: | " + e.getMessage());
            return null;
        }
    }

    public UsuarioPagina saveUsuario(UsuarioPagina user){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Guardando usuario: " + user.getNombre());
            return usuarioRepository.save(user);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al guardar usuario: | " + e.getMessage());
            return null;
        }
    }

    // se usa este metodo para poder buscar al usuario
    public UsuarioPagina getUsuarioId(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Trayendo a usuario | ID: " + id);
            return usuarioRepository.findById(id).orElse(null);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al traer usuario: | " + e.getMessage());
            return null;
        }   
    }

    public UsuarioPagina updateUsuario(UsuarioPagina user){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Actualizando a usuario | ID: " + user.getIdUsuario());
            if(!usuarioRepository.existsById(user.getIdUsuario())){
                return null;
            }
            return usuarioRepository.save(user);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al actualizar usuario: | " + e.getMessage());
            return null;
        }
    }

    public void deleteUsuario(int id){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Borrando usuario | ID: " + id);
            usuarioRepository.deleteById(id);
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al borrar usuario: | " + e.getMessage());
        }
    }

    public Usuario_DTO getUsuarioByUsername(String username){
        try{
            System.out.println("[" + LocalDateTime.now() + "] " + "Buscando usuario: " + username);
            UsuarioPagina user = usuarioRepository.findByNombre(username);
            if(user != null){
                System.out.println("[" + LocalDateTime.now() + "] " + "Usuario encontrado: " + user.getIdUsuario());
                return new Usuario_DTO(
                    user.getNombre(),
                    user.getGustoPrincipal(),
                    user.getGustoSecundario()
                );
            }else{
                System.out.println("[" + LocalDateTime.now() + "] " + "Usuario no encontrado");
                return null;
            }
        }catch(Exception e){
            System.out.println("[" + LocalDateTime.now() + "] " + "Error al buscar usuario: | " + e.getMessage());
            return null;
        }
    }
}
