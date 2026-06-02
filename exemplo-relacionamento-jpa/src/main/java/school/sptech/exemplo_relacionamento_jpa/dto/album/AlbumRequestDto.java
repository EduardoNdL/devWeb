package school.sptech.exemplo_relacionamento_jpa.dto.album;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public class AlbumRequestDto {

    @NotBlank
    @Schema(example = "The Dark Side of the Moon", description = "Nome do album")
    private String nome;

    @NotNull
    @PastOrPresent
    @Schema(example = "1973-03-01", description = "Data de lançamento do album")
    private LocalDate dataLancamento;

    public AlbumRequestDto() {
    }

    public AlbumRequestDto(String nome, LocalDate dataLancamento) {
        this.nome = nome;
        this.dataLancamento = dataLancamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
}
