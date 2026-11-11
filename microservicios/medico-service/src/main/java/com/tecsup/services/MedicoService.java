package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.*;
import com.tecsup.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicoService {
    private final EspecialidadRepository especialidades;
    private final MedicoRepository medicos;
    private final HorarioMedicoRepository horarios;
    public MedicoService(EspecialidadRepository especialidades, MedicoRepository medicos, HorarioMedicoRepository horarios) { this.especialidades=especialidades; this.medicos=medicos; this.horarios=horarios; }
    @Transactional(readOnly=true) public List<Especialidad> listarEspecialidades() { return especialidades.findAll(); }
    public Especialidad crearEspecialidad(Especialidad e) { return especialidades.save(e); }
    @Transactional(readOnly=true) public List<Medico> listarMedicos() { return medicos.findAll(); }
    @Transactional(readOnly=true) public Medico obtenerMedico(Long id) { return medicos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado: " + id)); }
    public Medico crearMedico(Medico m) { m.setEspecialidad(especialidades.findById(m.getIdEspecialidad()).orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada: " + m.getIdEspecialidad()))); return medicos.save(m); }
    public Medico actualizarMedico(Long id, Medico datos) {
        Medico actual=obtenerMedico(id); actual.setCmp(datos.getCmp()); actual.setNombres(datos.getNombres()); actual.setApellidos(datos.getApellidos()); actual.setTelefono(datos.getTelefono()); actual.setCorreo(datos.getCorreo()); actual.setEstado(datos.getEstado());
        if (datos.getIdEspecialidad()!=null) actual.setEspecialidad(especialidades.findById(datos.getIdEspecialidad()).orElseThrow(() -> new ResourceNotFoundException("Especialidad no encontrada: " + datos.getIdEspecialidad())));
        return medicos.save(actual);
    }
    public void eliminarMedico(Long id) { medicos.delete(obtenerMedico(id)); }
    @Transactional(readOnly=true) public List<HorarioMedico> listarHorarios() { return horarios.findAll(); }
    public HorarioMedico crearHorario(HorarioMedico h) { h.setMedico(obtenerMedico(h.getIdMedico())); return horarios.save(h); }
}
