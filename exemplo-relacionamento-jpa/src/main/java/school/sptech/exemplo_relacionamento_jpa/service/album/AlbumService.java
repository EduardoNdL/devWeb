package school.sptech.exemplo_relacionamento_jpa.service.album;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.exemplo_relacionamento_jpa.exception.EntidadeNaoEncontradaException;
import school.sptech.exemplo_relacionamento_jpa.model.album.Album;
import school.sptech.exemplo_relacionamento_jpa.repository.album.AlbumRepository;
import school.sptech.exemplo_relacionamento_jpa.repository.musica.MusicaRepository;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final MusicaRepository musicaRepository;

    public AlbumService(AlbumRepository albumRepository, MusicaRepository musicaRepository) {
        this.albumRepository = albumRepository;
        this.musicaRepository = musicaRepository;
    }

    public List<Album> listar() {
        return albumRepository.findAll();
    }

    public Album buscarPorId(Integer id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Album de id: %d não encontrado".formatted(id)));
    }

    public Album cadastrar(Album album) {
        return albumRepository.save(album);
    }

    public Album atualizar(Integer id, Album albumAtualizado) {
        // Verificamos se o album existe antes de atualizar.
        // Sem essa checagem, o save() faria um INSERT em vez de UPDATE
        // caso o id não existisse no banco.
        if (!albumRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException(
                    "Album de id: %d não encontrado".formatted(id));
        }
        albumAtualizado.setId(id);
        return albumRepository.save(albumAtualizado);
    }

    // @Transactional aqui garante que as duas operações abaixo sejam atômicas:
    // se deleteById falhar após removerMusicasPorIdAlbum já ter executado,
    // o banco desfaz tudo (rollback) e não ficamos com músicas órfãs.
    // Regra: sempre que um método de service executar MAIS DE UMA operação
    // no banco, marque-o com @Transactional.
    @Transactional
    public void deletar(Integer id) {
        if (!albumRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException(
                    "Album de id: %d não encontrado".formatted(id));
        }

        musicaRepository.removerMusicasPorIdAlbum(id);

        albumRepository.deleteById(id);
    }
}
