package sptech.school.exemplo_map.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mangas")
@RequiredArgsConstructor
public class MangaController {

    private final MangaService mangaService;
    private final MangaMapper mangaMapper;

    @GetMapping
    public ResponseEntity<Page<MangaResponseDto>> listar(
            @PageableDefault(
                    size = 10,
                    page = 0,
                    sort = "titulo",
                    direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok(
                mangaService.listar(pageable)
                        .map(mangaMapper::toResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaResponseDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(mangaMapper.toResponse(mangaService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<MangaResponseDto> criar(@RequestBody MangaRequestDto dto) {
        Manga manga = MangaMapperManual.toEntity(dto);
        return ResponseEntity.status(201)
                .body(mangaMapper.toResponse(mangaService.criar(manga, dto.getAutorId())));
    }
}
