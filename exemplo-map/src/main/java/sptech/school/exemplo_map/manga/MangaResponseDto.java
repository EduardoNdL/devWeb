package sptech.school.exemplo_map.manga;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MangaResponseDto {
    private Integer id;
    private String titulo;
    private LocalDate dataPublicacao;
    private String editora;
    private String genero;
    private Integer volumes;
    private AutorDto autor;

    @Getter
    @Setter
    public static class AutorDto {
        private Integer id;
        private String nome;
        private LocalDate dataNascimento;
    }
}
