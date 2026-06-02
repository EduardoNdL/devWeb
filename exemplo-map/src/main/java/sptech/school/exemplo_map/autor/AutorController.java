package sptech.school.exemplo_map.autor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorResponseDto>> listar() {
        return ResponseEntity.ok(
                autorService.listar().stream()
                        .map(AutorMapperManual::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(AutorMapperManual.toResponse(autorService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<AutorResponseDto> criar(@RequestBody AutorRequestDto dto) {
        return ResponseEntity.status(201)
                .body(AutorMapperManual.toResponse(autorService.criar(AutorMapperManual.toEntity(dto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponseDto> atualizar(@PathVariable Integer id,
                                                       @RequestBody AutorRequestDto dto) {
        return ResponseEntity.ok(AutorMapperManual.toResponse(
                autorService.atualizar(id, AutorMapperManual.toEntity(dto))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        autorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
