package school.sptech.exemplo_mock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados de entrada para atualizar uma simulacao de compra de dolar.")
public record SimulacaoAtualizacaoDto(
        @Schema(description = "Descricao curta da simulacao.", example = "Compra para intercambio")
        @NotBlank(message = "A descricao e obrigatoria")
        @Size(max = 120, message = "A descricao deve ter no maximo 120 caracteres")
        String descricao,

        @Schema(description = "Valor em reais disponivel para a compra.", example = "2300.0")
        @NotNull(message = "O valor em reais e obrigatorio")
        @DecimalMin(value = "0.01", message = "O valor em reais deve ser maior que zero")
        Double valorEmReais,

        @Schema(description = "Cotacao maxima que a pessoa aceita pagar.", example = "5.10")
        @NotNull(message = "A cotacao desejada e obrigatoria")
        @DecimalMin(value = "0.01", message = "A cotacao desejada deve ser maior que zero")
        Double cotacaoDesejada
) {
}
