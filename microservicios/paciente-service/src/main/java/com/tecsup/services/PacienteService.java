package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Paciente;
import com.tecsup.repository.PacienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PacienteService {
    private final PacienteRepository repository;
    public PacienteService(PacienteRepository repository) { this.repository = repository; }
    @Transactional(readOnly = true) public List<Paciente> listar() { return repository.findAll(); }
    @Transactional(readOnly = true) public Paciente obtener(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado: " + id));
    }
    public Paciente guardar(Paciente paciente) { return repository.save(paciente); }
    public Paciente actualizar(Long id, Paciente datos) {
        Paciente actual = obtener(id);
        actual.setDni(datos.getDni()); actual.setNombres(datos.getNombres()); actual.setApellidos(datos.getApellidos());
        actual.setFechaNacimiento(datos.getFechaNacimiento()); actual.setSexo(datos.getSexo()); actual.setTelefono(datos.getTelefono());
        actual.setDireccion(datos.getDireccion()); actual.setCorreo(datos.getCorreo()); actual.setEstado(datos.getEstado());
        return repository.save(actual);
    }
    public void eliminar(Long id) { repository.delete(obtener(id)); }
}
