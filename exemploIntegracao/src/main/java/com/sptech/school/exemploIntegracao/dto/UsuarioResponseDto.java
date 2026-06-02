package com.sptech.school.exemploIntegracao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record UsuarioResponseDto (
        Long id,

        @JsonProperty(value = "first_name")
        String firstName,

        @JsonProperty(value = "last_name")
        String lastName,

        @JsonProperty(value = "created_at")
        LocalDateTime createdAt){}
