package com.tecsup.controller;

import com.tecsup.model.Cita;
import com.tecsup.dto.EstadoCitaRequest;
import com.tecsup.services.CitaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citas")
@Validated
public class CitaController {
    private final CitaService service;
    public CitaController(CitaService service) { this.service=service; }
    @GetMapping public List<Cita> listar() { return service.listar(); }
    @GetMapping("/{id}") public Cita obtener(@PathVariable Long id) { return service.obtener(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Cita crear(@Valid @RequestBody Cita cita) { return service.crear(cita); }
    @PutMapping("/{id}") public Cita actualizar(@PathVariable Long id, @Valid @RequestBody Cita cita) { return service.actualizar(id,cita); }
    @PatchMapping("/{id}/estado") public Cita cambiarEstado(@PathVariable Long id, @Valid @RequestBody EstadoCitaRequest request) { return service.cambiarEstado(id,request.estado()); }
    @PatchMapping("/{id}/cancelar") public Cita cancelar(@PathVariable Long id) { return service.cancelar(id); }
    @DeleteMapping("/{id}") public Cita eliminar(@PathVariable Long id) { return service.cancelar(id); }
}
