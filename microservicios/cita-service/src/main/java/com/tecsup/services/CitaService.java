package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Cita;
import com.tecsup.model.EstadoCita;
import com.tecsup.repository.CitaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CitaService {
    private final CitaRepository repository;
    public CitaService(CitaRepository repository) { this.repository=repository; }
    @Transactional(readOnly=true) public List<Cita> listar() { return repository.findAll(); }
    @Transactional(readOnly=true) public Cita obtener(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada: " + id)); }
    public Cita crear(Cita cita) { cita.setEstado(EstadoCita.PROGRAMADA); return repository.save(cita); }
    public Cita actualizar(Long id, Cita datos) {
        Cita actual=obtener(id); actual.setFecha(datos.getFecha()); actual.setHora(datos.getHora()); actual.setIdPaciente(datos.getIdPaciente()); actual.setIdMedico(datos.getIdMedico()); actual.setMotivo(datos.getMotivo());
        return repository.save(actual);
    }
    public Cita cambiarEstado(Long id, EstadoCita estado) { Cita cita=obtener(id); cita.setEstado(estado); return repository.save(cita); }
    public Cita cancelar(Long id) { return cambiarEstado(id, EstadoCita.CANCELADA); }
}
