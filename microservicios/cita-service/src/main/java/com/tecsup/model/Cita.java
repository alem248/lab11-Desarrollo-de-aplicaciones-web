package com.tecsup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cita")
public class Cita {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_cita") private Long idCita;
    @NotNull @Column(nullable = false) private LocalDate fecha;
    @NotNull @Column(nullable = false) private LocalTime hora;
    @NotNull @Column(name = "id_paciente", nullable = false) private Long idPaciente;
    @NotNull @Column(name = "id_medico", nullable = false) private Long idMedico;
    @Size(max = 300) private String motivo;
    @NotNull @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private EstadoCita estado = EstadoCita.PROGRAMADA;
    public Long getIdCita() { return idCita; }
    public void setIdCita(Long idCita) { this.idCita=idCita; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha=fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora=hora; }
    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente=idPaciente; }
    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico=idMedico; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo=motivo; }
    public EstadoCita getEstado() { return estado; }
    public void setEstado(EstadoCita estado) { this.estado=estado; }
}
