package com.sptech.school.exemploIntegracao.controller;

import com.sptech.school.exemploIntegracao.UsuarioClient;
import com.sptech.school.exemploIntegracao.dto.UsuarioRequestDto;
import com.sptech.school.exemploIntegracao.dto.UsuarioResponseDto;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioClient usuarioClient;

    public UsuarioController(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioResponseDto>> listar(){
        return ResponseEntity.ok(usuarioClient.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id){
        try {
            return ResponseEntity.ok(usuarioClient.buscarPorId(id));
        } catch (FeignException ex){
            int status = ex.status();

            if(status == 404) {
                return ResponseEntity.notFound().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody UsuarioRequestDto usuarioRequestDto){
        UsuarioResponseDto usuarioResponseDto = usuarioClient.cadastrar(usuarioRequestDto);

        return ResponseEntity.created(null).body(usuarioResponseDto);
    }
}
