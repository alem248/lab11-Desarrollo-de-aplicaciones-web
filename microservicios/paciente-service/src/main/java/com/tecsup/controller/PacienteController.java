package com.tecsup.controller;

import com.tecsup.model.Paciente;
import com.tecsup.services.PacienteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {
    private final PacienteService service;
    public PacienteController(PacienteService service) { this.service = service; }
    @GetMapping public List<Paciente> listar() { return service.listar(); }
    @GetMapping("/{id}") public Paciente obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Paciente crear(@Valid @RequestBody Paciente paciente) { return service.guardar(paciente); }
    @PutMapping("/{id}") public Paciente actualizar(@PathVariable Long id, @Valid @RequestBody Paciente paciente) { return service.actualizar(id, paciente); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
