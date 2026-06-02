package sptech.school.exemplo_map.autor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AutorRequestDto {

    @Schema(description = "Nome completo do autor", example = "Naoki Urasawa")
    private String nome;

    @Schema(description = "Data de nascimento do autor", example = "1960-01-02")
    private LocalDate dataNascimento;
}
