package school.sptech.exemplo_relacionamento_jpa.dto;

import school.sptech.exemplo_relacionamento_jpa.model.Musica;
import school.sptech.exemplo_relacionamento_jpa.model.Album;

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

        Album album = model.getAlbum();

        MusicaResponseDto.AlbumMusicaDto albumMusicaDto =
                new MusicaResponseDto.AlbumMusicaDto(
                        album.getId(),
                        album.getNome()
                );

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
