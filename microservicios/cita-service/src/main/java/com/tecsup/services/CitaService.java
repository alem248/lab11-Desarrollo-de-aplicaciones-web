package com.tecsup.services;

import com.tecsup.exception.ResourceNotFoundException;
import com.tecsup.model.Cita;
import com.tecsup.model.EstadoCita;
import com.tecsup.repository.CitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

@Service
@Transactional
public class CitaService {
    private final CitaRepository repository;
    private final RestClient restClient;
    private final String pacienteUrl;
    private final String medicoUrl;
    public CitaService(CitaRepository repository, RestClient.Builder builder,
                       @Value("${servicios.paciente-url}") String pacienteUrl,
                       @Value("${servicios.medico-url}") String medicoUrl) {
        this.repository=repository; this.restClient=builder.build();
        this.pacienteUrl=pacienteUrl; this.medicoUrl=medicoUrl;
    }
    @Transactional(readOnly=true) public List<Cita> listar() { return repository.findAll(); }
    @Transactional(readOnly=true) public Cita obtener(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada: " + id)); }
    public Cita crear(Cita cita) { validarReferencias(cita); cita.setEstado(EstadoCita.PROGRAMADA); return repository.save(cita); }
    public Cita actualizar(Long id, Cita datos) {
        validarReferencias(datos);
        Cita actual=obtener(id); actual.setFecha(datos.getFecha()); actual.setHora(datos.getHora()); actual.setIdPaciente(datos.getIdPaciente()); actual.setIdMedico(datos.getIdMedico()); actual.setMotivo(datos.getMotivo());
        return repository.save(actual);
    }
    public Cita cambiarEstado(Long id, EstadoCita estado) { Cita cita=obtener(id); cita.setEstado(estado); return repository.save(cita); }
    public Cita cancelar(Long id) { return cambiarEstado(id, EstadoCita.CANCELADA); }
    private void validarReferencias(Cita cita) {
        restClient.get().uri(pacienteUrl + "/api/pacientes/{id}", cita.getIdPaciente()).retrieve().toBodilessEntity();
        restClient.get().uri(medicoUrl + "/api/medicos/{id}", cita.getIdMedico()).retrieve().toBodilessEntity();
    }
}
