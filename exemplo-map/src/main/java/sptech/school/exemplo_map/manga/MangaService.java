package sptech.school.exemplo_map.manga;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.exemplo_map.autor.Autor;
import sptech.school.exemplo_map.autor.AutorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MangaService {

    private final MangaRepository mangaRepository;
    private final AutorRepository autorRepository;

    public Page<Manga> listar(Pageable pageable) {
        return mangaRepository.findAll(pageable);
    }

    public Manga buscarPorId(Integer id) {
        return mangaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Manga não encontrado: " + id));
    }

    public Manga criar(Manga manga, Integer autorId) {

        Autor autor = autorRepository.findById(autorId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado: " + autorId));

        manga.setAutor(autor);

        return mangaRepository.save(manga);
    }
}
