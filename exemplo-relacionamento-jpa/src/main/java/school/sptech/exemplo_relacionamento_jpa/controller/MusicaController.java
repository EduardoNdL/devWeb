package school.sptech.exemplo_relacionamento_jpa.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.exemplo_relacionamento_jpa.dto.MusicaMapper;
import school.sptech.exemplo_relacionamento_jpa.dto.MusicaRequestDto;
import school.sptech.exemplo_relacionamento_jpa.dto.MusicaResponseDto;
import school.sptech.exemplo_relacionamento_jpa.model.Musica;
import school.sptech.exemplo_relacionamento_jpa.service.MusicaService;

import java.util.List;

@RestController
@RequestMapping("/musicas")
@Tag(name = "Musicas", description = "API de gerenciamento de categorias") // <- Isso muda o nome
public class MusicaController {


    private final MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @GetMapping
    public ResponseEntity<List<MusicaResponseDto>> listar() {
        List<Musica> musicas = musicaService.listar();
        if (musicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<MusicaResponseDto> resposta = MusicaMapper.toDto(musicas);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaResponseDto> buscarPorId(
            @PathVariable Integer id
    ) {
        Musica musica = musicaService.findById(id);
        MusicaResponseDto resposta = MusicaMapper.toDto(musica);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<MusicaResponseDto> cadastrar(
            @Valid @RequestBody MusicaRequestDto dto
    ) {
        Musica entidade = MusicaMapper.toEntity(dto);
        Musica musicaCadastrada = musicaService.cadastrar(entidade, dto.getAlbumId());
        MusicaResponseDto resposta = MusicaMapper.toDto(musicaCadastrada);
        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping("/media")
    public ResponseEntity<Double> buscarMedia(){
        return ResponseEntity.ok(musicaService.media());
    }

    @GetMapping("/albuns")
    public ResponseEntity<List<MusicaResponseDto>> buscarPorNomeAlbum(
            @RequestParam String nome
    ){
        return ResponseEntity.ok(musicaService.buscarMusicasPorNomeAlbum(nome)
                .stream()
                .map(MusicaMapper::toDto)
                .toList());
    }
}
