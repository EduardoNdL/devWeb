package school.sptech.exemplo_relacionamento_jpa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public class MusicaRequestDto {

    @NotBlank
    @Schema(example = "Hurt Feelings", description = "Representa o nome da musica")
    private String nome;

    @NotNull
    @Positive
    @Schema(example = "1", description = "Representa o id do albúm relacionado")
    private Integer albumId;

    @DecimalMax(value = "10.0")
    @DecimalMin(value = "1.0")
    @Schema(example = "9.9", description = "Representa a nota da musica")
    private Double nota;

    public MusicaRequestDto() {
    }

    public MusicaRequestDto(String nome, Integer albumId, Double nota) {
        this.nome = nome;
        this.albumId = albumId;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
