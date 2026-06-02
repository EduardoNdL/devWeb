package sptech.school.exemplo_map.manga;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MangaRequestDto {

    @Schema(description = "Título do mangá", example = "Fullmetal Alchemist")
    private String titulo;

    @Schema(description = "Data de publicação do primeiro volume", example = "2001-07-12")
    private LocalDate dataPublicacao;

    @Schema(description = "Editora responsável pela publicação", example = "Square Enix")
    private String editora;

    @Schema(description = "Gênero principal do mangá", example = "Aventura/Fantasia")
    private String genero;

    @Schema(description = "Número total de volumes", example = "27")
    private Integer volumes;

    @Schema(description = "ID do autor já cadastrado", example = "3")
    private Integer autorId;
}
