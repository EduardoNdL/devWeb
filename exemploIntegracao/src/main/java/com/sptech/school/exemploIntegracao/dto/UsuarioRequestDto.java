package com.sptech.school.exemploIntegracao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record UsuarioRequestDto (
        @Schema(defaultValue = "Edu")
        @JsonProperty(value = "first_name")
        String firstName,

        @Schema(defaultValue = "Nunes")
        @JsonProperty(value = "last_name")
        String lastName,

        @JsonProperty(value = "created_at")
        LocalDateTime createdAt){}
