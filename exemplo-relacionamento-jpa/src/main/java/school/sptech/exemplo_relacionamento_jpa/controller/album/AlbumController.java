package school.sptech.exemplo_relacionamento_jpa.controller.album;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.exemplo_relacionamento_jpa.dto.album.AlbumMapper;
import school.sptech.exemplo_relacionamento_jpa.dto.album.AlbumRequestDto;
import school.sptech.exemplo_relacionamento_jpa.dto.album.AlbumResponseDto;
import school.sptech.exemplo_relacionamento_jpa.model.album.Album;
import school.sptech.exemplo_relacionamento_jpa.service.album.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/albuns")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumResponseDto>> listar() {
        List<Album> albuns = albumService.listar();
        if (albuns.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(AlbumMapper.toDto(albuns));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> buscarPorId(@PathVariable Integer id) {
        Album album = albumService.buscarPorId(id);
        return ResponseEntity.ok(AlbumMapper.toDto(album));
    }

    @PostMapping
    public ResponseEntity<AlbumResponseDto> cadastrar(
            @Valid @RequestBody AlbumRequestDto dto
    ) {
        Album album = albumService.cadastrar(AlbumMapper.toEntity(dto));
        return ResponseEntity.status(201).body(AlbumMapper.toDto(album));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AlbumRequestDto dto
    ) {
        Album albumAtualizado = albumService.atualizar(id, AlbumMapper.toEntity(dto));
        return ResponseEntity.ok(AlbumMapper.toDto(albumAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        albumService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/teste")
    public ResponseEntity<List<Album>> teste(){
        List<Album> albuns = albumService.listar();
        return ResponseEntity.ok(albuns);
    }
}
