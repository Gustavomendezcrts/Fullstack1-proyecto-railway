package cl.FilmFlux.recomendacionApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.FilmFlux.recomendacionApp.DTO.Usuario_DTO;
import cl.FilmFlux.recomendacionApp.model.UsuarioPagina;
import cl.FilmFlux.recomendacionApp.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/v1/usuarios")
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario_DTO>> getUsuarios(){
        List<Usuario_DTO> usuarios = usuarioService.getUsuarios();
        if(usuarios.isEmpty()){
            System.out.println("[" + java.time.LocalDateTime.now() + "] " + "No se encontraron usuarios");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else{
            return ResponseEntity.ok(usuarioService.getUsuarios());
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioPagina> saveUsuario(@Valid @RequestBody UsuarioPagina usuario){
        if(usuario == null){
            System.out.println("[" + java.time.LocalDateTime.now() + "] " + "Error al guardar usuario: Datos de usuario no proporcionados");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario_DTO> getUsuarioById(@PathVariable int id){
        
        UsuarioPagina usuario = usuarioService.getUsuarioId(id);
        if(usuario == null){
            System.out.println("[" + java.time.LocalDateTime.now() + "] " + "No se encontró usuario con ID: " + id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioPagina> actualizarUsuario(@PathVariable int id, @Valid @RequestBody UsuarioPagina usuario) {
        usuario.setIdUsuario(id);
        UsuarioPagina usuarioActualizado = usuarioService.updateUsuario(usuario);
        if (usuarioActualizado == null) {
            System.out.println("[" + java.time.LocalDateTime.now() + "] " + "No se encontró usuario con ID: " + id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(usuarioActualizado);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        if(usuarioService.getUsuarioId(id) == null){
             System.out.println("[" + java.time.LocalDateTime.now() + "] " + "No se encontró usuario con ID: " + id);
             return ResponseEntity.noContent().build();
        }else{
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Usuario_DTO> getUsuarioByUsername(@PathVariable String username){
        Usuario_DTO usuario = usuarioService.getUsuarioByUsername(username);
        if(usuario == null){
            System.out.println("[" + java.time.LocalDateTime.now() + "] " + "No se encontró usuario: " + username);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarioService.getUsuarioByUsername(username));
    }
}
