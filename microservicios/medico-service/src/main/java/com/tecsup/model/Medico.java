package com.tecsup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "medico", uniqueConstraints = @UniqueConstraint(columnNames = "cmp"))
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_medico") private Long idMedico;
    @Size(max = 20) @Column(unique = true, length = 20) private String cmp;
    @NotBlank @Size(max = 100) @Column(nullable = false, length = 100) private String nombres;
    @NotBlank @Size(max = 100) @Column(nullable = false, length = 100) private String apellidos;
    @Size(max = 15) private String telefono;
    @Email @Size(max = 100) private String correo;
    @ManyToOne(optional = false) @JoinColumn(name = "id_especialidad", nullable = false) private Especialidad especialidad;
    @Column(length = 1, nullable = false) private String estado = "A";
    @Transient private Long idEspecialidad;
    @PostLoad @PostPersist private void cargarIdEspecialidad() { if (especialidad != null) idEspecialidad = especialidad.getIdEspecialidad(); }
    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long id) { this.idMedico = id; }
    public String getCmp() { return cmp; }
    public void setCmp(String cmp) { this.cmp = cmp; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; cargarIdEspecialidad(); }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Long getIdEspecialidad() { return idEspecialidad; }
    public void setIdEspecialidad(Long idEspecialidad) { this.idEspecialidad = idEspecialidad; }
}
