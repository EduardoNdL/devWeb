package school.sptech.exemplo_relacionamento_jpa.dto.musica;

import school.sptech.exemplo_relacionamento_jpa.model.album.Album;
import school.sptech.exemplo_relacionamento_jpa.model.musica.Musica;

import java.util.List;

public class MusicaMapper {

    public static Musica toEntity(MusicaRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Musica entidade = new Musica();
        entidade.setNome(dto.getNome());
        entidade.setNota(dto.getNota());

        return entidade;
    }

    public static MusicaResponseDto toDto(Musica model) {
        if (model == null) {
            return null;
        }

        Album albumEntidade = model.getAlbum();

        MusicaResponseDto.AlbumMusicaDto albumMusicaDto = new
                MusicaResponseDto.AlbumMusicaDto();

        albumMusicaDto.setId(albumEntidade.getId());
        albumMusicaDto.setNome(albumEntidade.getNome());

        MusicaResponseDto dto = new MusicaResponseDto(
                model.getId(),
                model.getNome(),
                albumMusicaDto,
                model.getNota()
        );

        return dto;
    }

    public static List<MusicaResponseDto> toDto(List<Musica> entities) {
        return entities.stream()
                .map(MusicaMapper::toDto)
                .toList();
    }
}
