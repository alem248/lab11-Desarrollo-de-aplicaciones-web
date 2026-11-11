package com.tecsup.controller;

import com.tecsup.model.*;
import com.tecsup.services.MedicoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MedicoController {
    private final MedicoService service;
    public MedicoController(MedicoService service) { this.service=service; }
    @GetMapping("/api/especialidades") public List<Especialidad> listarEspecialidades() { return service.listarEspecialidades(); }
    @PostMapping("/api/especialidades") @ResponseStatus(HttpStatus.CREATED) public Especialidad crearEspecialidad(@Valid @RequestBody Especialidad e) { return service.crearEspecialidad(e); }
    @GetMapping("/api/medicos") public List<Medico> listar() { return service.listarMedicos(); }
    @GetMapping("/api/medicos/{id}") public Medico obtener(@PathVariable Long id) { return service.obtenerMedico(id); }
    @PostMapping("/api/medicos") @ResponseStatus(HttpStatus.CREATED) public Medico crear(@Valid @RequestBody Medico m) { return service.crearMedico(m); }
    @PutMapping("/api/medicos/{id}") public Medico actualizar(@PathVariable Long id, @Valid @RequestBody Medico m) { return service.actualizarMedico(id,m); }
    @DeleteMapping("/api/medicos/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminarMedico(id); }
    @GetMapping("/api/horarios") public List<HorarioMedico> horarios() { return service.listarHorarios(); }
    @PostMapping("/api/horarios") @ResponseStatus(HttpStatus.CREATED) public HorarioMedico crearHorario(@Valid @RequestBody HorarioMedico h) { return service.crearHorario(h); }
}
