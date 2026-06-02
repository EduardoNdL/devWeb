package com.sptech.school.exemploIntegracao;

import com.sptech.school.exemploIntegracao.dto.UsuarioRequestDto;
import com.sptech.school.exemploIntegracao.dto.UsuarioResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "usuario.service",
        url = "${gateway.usuario.url}",
        configuration = UsuarioClientDecoder.class)
public interface UsuarioClient {
    @GetMapping
    List<UsuarioResponseDto> listar();

    @GetMapping("/{id}")
    UsuarioResponseDto buscarPorId(@PathVariable Long id);

    @PostMapping()
    UsuarioResponseDto cadastrar(@RequestBody UsuarioRequestDto usuarioResponseDto);
}
