package school.sptech.exemplo_relacionamento_jpa.service;

import org.springframework.stereotype.Service;
import school.sptech.exemplo_relacionamento_jpa.dto.MusicaResponseDto;
import school.sptech.exemplo_relacionamento_jpa.exception.EntidadeNaoEncontradaException;
import school.sptech.exemplo_relacionamento_jpa.model.Album;
import school.sptech.exemplo_relacionamento_jpa.model.Musica;
import school.sptech.exemplo_relacionamento_jpa.repository.AlbumRepository;
import school.sptech.exemplo_relacionamento_jpa.repository.MusicaRepository;

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
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Musica de id: %d não encontrada".formatted(id)));
    }

    public Musica cadastrar(Musica musicaParaCadastro, Integer albumId){
        Album album = albumRepository.findById(albumId).orElseThrow(
                () -> new EntidadeNaoEncontradaException("Album não encontrado")
        );
        musicaParaCadastro.setAlbum(album);
        return musicaRepository.save(musicaParaCadastro);
    }

    public Double media(){
        return musicaRepository.getMedia();
    }

    public List<Musica> buscarMusicasPorNomeAlbum(String nome){
        return musicaRepository.findByAlbumNome(nome);
    }
}
