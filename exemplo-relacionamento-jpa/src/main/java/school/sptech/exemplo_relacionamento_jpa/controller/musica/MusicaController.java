package school.sptech.exemplo_relacionamento_jpa.controller.musica;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.exemplo_relacionamento_jpa.dto.musica.MusicaMapper;
import school.sptech.exemplo_relacionamento_jpa.dto.musica.MusicaRequestDto;
import school.sptech.exemplo_relacionamento_jpa.dto.musica.MusicaResponseDto;
import school.sptech.exemplo_relacionamento_jpa.model.musica.Musica;
import school.sptech.exemplo_relacionamento_jpa.service.musica.MusicaService;

import java.util.List;

@RestController
@RequestMapping("/musicas")
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
        return ResponseEntity.ok(MusicaMapper.toDto(musicas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaResponseDto> buscarPorId(@PathVariable Integer id) {
        Musica musica = musicaService.findById(id);
        return ResponseEntity.ok(MusicaMapper.toDto(musica));
    }

    @PostMapping
    public ResponseEntity<MusicaResponseDto> cadastrar(
            @Valid @RequestBody MusicaRequestDto dto
    ) {
        Musica entidade = MusicaMapper.toEntity(dto);
        Musica musicaCadastrada = musicaService.cadastrar(entidade, dto.getAlbumId());
        return ResponseEntity.status(201).body(MusicaMapper.toDto(musicaCadastrada));
    }

    @GetMapping("/media")
    public ResponseEntity<Double> buscarMedia() {
        Double valor = musicaService.buscarMedia();
        return ResponseEntity.ok(valor);
    }

    @GetMapping("/albuns")
    public ResponseEntity<List<MusicaResponseDto>> buscarPorNomeAlbum(
            @RequestParam String nome
    ) {
        List<Musica> musicas = musicaService.buscarPorNomeAlbum(nome);
        if (musicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MusicaMapper.toDto(musicas));
    }

    @GetMapping("/albuns/jpql")
    public ResponseEntity<List<MusicaResponseDto>> buscarPorNomeAlbumJpql(
            @RequestParam String nome
    ) {
        List<Musica> musicas = musicaService.buscarPorNomeAlbumJpql(nome);
        if (musicas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(MusicaMapper.toDto(musicas));
    }

    @DeleteMapping("/albuns/{albumId}")
    public ResponseEntity<Void> removerPorAlbum(@PathVariable Integer albumId) {
        musicaService.removerPorAlbum(albumId);
        return ResponseEntity.noContent().build();
    }
}
