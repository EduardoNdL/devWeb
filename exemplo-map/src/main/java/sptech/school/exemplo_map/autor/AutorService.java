package sptech.school.exemplo_map.autor;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.exemplo_map.manga.MangaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;
    private final MangaRepository mangaRepository;

    public List<Autor> listar() {
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Integer id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado: " + id));
    }

    public Autor criar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Autor atualizar(Integer id, Autor autorAtualizado) {
        Autor autor = buscarPorId(id);
        autor.setNome(autorAtualizado.getNome());
        autor.setDataNascimento(autorAtualizado.getDataNascimento());
        return autorRepository.save(autor);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!autorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado: " + id);
        }
        mangaRepository.deleteByAutorId(id);
        autorRepository.deleteById(id);
    }
}
