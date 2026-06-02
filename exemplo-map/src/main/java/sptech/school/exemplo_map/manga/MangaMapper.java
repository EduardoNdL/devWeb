package sptech.school.exemplo_map.manga;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MangaMapper {
    MangaResponseDto toResponse(Manga manga);
}
