package sptech.school.exemplo_map.autor;

public class AutorMapperManual {

    public static Autor toEntity(AutorRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        autor.setDataNascimento(dto.getDataNascimento());
        return autor;
    }

    public static AutorResponseDto toResponse(Autor autor) {
        if (autor == null) {
            return null;
        }
        AutorResponseDto dto = new AutorResponseDto();
        dto.setId(autor.getId());
        dto.setNome(autor.getNome());
        dto.setDataNascimento(autor.getDataNascimento());
        return dto;
    }
}
