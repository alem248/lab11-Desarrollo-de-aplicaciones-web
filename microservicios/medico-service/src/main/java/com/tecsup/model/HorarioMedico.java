package com.tecsup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table(name = "horario_medico")
public class HorarioMedico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_horario") private Long idHorario;
    @ManyToOne(optional = false) @JoinColumn(name = "id_medico", nullable = false) private Medico medico;
    @Transient private Long idMedico;
    @NotBlank private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    @PostLoad @PostPersist private void cargarIdMedico() { if (medico != null) idMedico = medico.getIdMedico(); }
    public Long getIdHorario() { return idHorario; }
    public void setIdHorario(Long id) { this.idHorario = id; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; cargarIdMedico(); }
    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }
    public String getDiaSemana() { return diaSemana; }
    public void setDiaSemana(String diaSemana) { this.diaSemana = diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
}
