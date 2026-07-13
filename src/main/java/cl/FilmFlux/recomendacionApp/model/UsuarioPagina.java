package cl.FilmFlux.recomendacionApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios_pagina")
public class UsuarioPagina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @NotBlank
    private String nombre;
    @NotBlank
    private String email;
    @NotBlank
    private String contraseña;
    @NotBlank
    private String gustoPrincipal;
    @NotBlank
    private String gustoSecundario;
}
