package school.sptech.exemplo_mock.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resultado da simulacao com a cotacao atual do dolar.")
public record SimulacaoResultadoDto(
        @Schema(description = "Identificador da simulacao.", example = "1")
        Long id,
        @Schema(description = "Descricao da simulacao.", example = "Compra para viagem")
        String descricao,
        @Schema(description = "Valor em reais disponivel.", example = "1500.0")
        Double valorEmReais,
        @Schema(description = "Cotacao maxima que a pessoa aceita pagar.", example = "5.20")
        Double cotacaoDesejada,
        @Schema(description = "Cotacao atual do dolar retornada pela API externa.", example = "5.08")
        Double cotacaoAtual,
        @Schema(description = "Quantidade de dolares que seria possivel comprar agora.", example = "295.28")
        Double quantidadeDolares,
        @Schema(description = "Diferenca entre a cotacao atual e a cotacao desejada.", example = "-0.12")
        Double diferencaCotacao,
        @Schema(
                description = "Recomendacao de compra.",
                example = "COMPRAR_AGORA",
                allowableValues = {"COMPRAR_AGORA", "AGUARDAR"}
        )
        String recomendacao,
        @Schema(description = "Motivo textual da recomendacao.", example = "A cotacao atual ficou dentro do valor que voce queria pagar.")
        String mensagem
) {
}
