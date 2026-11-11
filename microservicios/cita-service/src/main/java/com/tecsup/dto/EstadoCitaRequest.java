package com.tecsup.dto;

import com.tecsup.model.EstadoCita;
import jakarta.validation.constraints.NotNull;

public record EstadoCitaRequest(@NotNull EstadoCita estado) { }
