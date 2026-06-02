package school.sptech.exemplo_relacionamento_jpa.service.musica;

import org.springframework.stereotype.Service;
import school.sptech.exemplo_relacionamento_jpa.exception.EntidadeNaoEncontradaException;
import school.sptech.exemplo_relacionamento_jpa.model.album.Album;
import school.sptech.exemplo_relacionamento_jpa.model.musica.Musica;
import school.sptech.exemplo_relacionamento_jpa.repository.album.AlbumRepository;
import school.sptech.exemplo_relacionamento_jpa.repository.musica.MusicaRepository;

import java.util.List;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;
    private final AlbumRepository albumRepository;

    public MusicaService(MusicaRepository musicaRepository, AlbumRepository albumRepository) {
        this.musicaRepository = musicaRepository;
        this.albumRepository = albumRepository;
    }

    public List<Musica> listar() {
        return musicaRepository.findAll();
    }

    public Musica findById(Integer id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Musica de id: %d não encontrada".formatted(id)));
    }

    public Musica cadastrar(Musica musicaParaCadastro, Integer albumId) {
        Album albumEncontrado = albumRepository.findById(albumId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Album de id: %d não encontrado".formatted(albumId)));

        musicaParaCadastro.setAlbum(albumEncontrado);

        return musicaRepository.save(musicaParaCadastro);
    }

    public Double buscarMedia() {
        return musicaRepository.getMedia();
    }

    public List<Musica> buscarPorNomeAlbum(String nome) {
        return musicaRepository.findByAlbumNome(nome);
    }

    public List<Musica> buscarPorNomeAlbumJpql(String nome) {
        return musicaRepository.buscarPorNomeDoAlbum(nome);
    }

    public void removerPorAlbum(Integer albumId) {
        if (!albumRepository.existsById(albumId)) {
            throw new EntidadeNaoEncontradaException(
                    "Album de id: %d não encontrado".formatted(albumId));
        }
        musicaRepository.removerMusicasPorIdAlbum(albumId);
    }
}
