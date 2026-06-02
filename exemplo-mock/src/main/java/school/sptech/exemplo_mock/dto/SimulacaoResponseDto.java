package school.sptech.exemplo_mock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representacao resumida de uma simulacao cadastrada.")
public record SimulacaoResponseDto(
        @Schema(description = "Identificador da simulacao.", example = "1")
        Long id,
        @Schema(description = "Descricao da simulacao.", example = "Compra para viagem")
        String descricao,
        @Schema(description = "Valor em reais disponivel.", example = "1500.0")
        Double valorEmReais,
        @Schema(description = "Cotacao desejada.", example = "5.20")
        Double cotacaoDesejada
) {
}
