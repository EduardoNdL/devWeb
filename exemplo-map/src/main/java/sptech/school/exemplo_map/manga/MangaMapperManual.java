package sptech.school.exemplo_map.manga;

public class MangaMapperManual {

    public static Manga toEntity(MangaRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Manga manga = new Manga();
        manga.setTitulo(dto.getTitulo());
        manga.setDataPublicacao(dto.getDataPublicacao());
        manga.setEditora(dto.getEditora());
        manga.setGenero(dto.getGenero());
        manga.setVolumes(dto.getVolumes());
        return manga;
    }

    public static MangaResponseDto toResponse(Manga manga) {
        if (manga == null) {
            return null;
        }

        MangaResponseDto dto = new MangaResponseDto();
        dto.setId(manga.getId());
        dto.setTitulo(manga.getTitulo());
        dto.setDataPublicacao(manga.getDataPublicacao());
        dto.setEditora(manga.getEditora());
        dto.setGenero(manga.getGenero());
        dto.setVolumes(manga.getVolumes());

        if (manga.getAutor() != null) {
            MangaResponseDto.AutorDto autorDto = new MangaResponseDto.AutorDto();
            autorDto.setId(manga.getAutor().getId());
            autorDto.setNome(manga.getAutor().getNome());
            autorDto.setDataNascimento(manga.getAutor().getDataNascimento());
            dto.setAutor(autorDto);
        }

        return dto;
    }
}
