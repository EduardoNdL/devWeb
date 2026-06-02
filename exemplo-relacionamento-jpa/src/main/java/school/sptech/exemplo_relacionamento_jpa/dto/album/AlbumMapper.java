package school.sptech.exemplo_relacionamento_jpa.dto.album;

import school.sptech.exemplo_relacionamento_jpa.model.album.Album;
import school.sptech.exemplo_relacionamento_jpa.model.musica.Musica;

import java.util.List;

public class AlbumMapper {

    public static Album toEntity(AlbumRequestDto dto) {
        Album album = new Album();
        album.setNome(dto.getNome());
        album.setDataLancamento(dto.getDataLancamento());
        return album;
    }

    public static AlbumResponseDto toDto(Album album) {

        List<AlbumResponseDto.MusicaAlbumDto> musicas = album.getMusicas()
                .stream()
                .map(m -> new AlbumResponseDto.MusicaAlbumDto(
                        m.getId(),
                        m.getNome()
                ))
                .toList();


        return new AlbumResponseDto(
                album.getId(),
                album.getNome(),
                album.getDataLancamento(),
                musicas
        );
    }

    public static List<AlbumResponseDto> toDto(List<Album> albuns) {
        return albuns.stream()
                .map(AlbumMapper::toDto)
                .toList();
    }
}
