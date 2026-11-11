package com.tecsup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "especialidad")
public class Especialidad {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_especialidad") private Long idEspecialidad;
    @NotBlank @Size(max = 100) @Column(nullable = false, length = 100) private String nombre;
    @Size(max = 200) private String descripcion;
    @Column(length = 1, nullable = false) private String estado = "A";
    public Long getIdEspecialidad() { return idEspecialidad; }
    public void setIdEspecialidad(Long id) { this.idEspecialidad = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
