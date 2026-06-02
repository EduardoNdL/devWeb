package sptech.school.exemplo_map.autor;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutorResponseDto {
    private Integer id;
    private String nome;
    private LocalDate dataNascimento;
}
