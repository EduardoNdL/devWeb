package school.sptech.exemplo_mock.exception.handler;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

@Schema(description = "Estrutura padrao para respostas de erro da API.")
public record ApiErrorResponse(
        @Schema(description = "Codigo resumido do erro.", example = "dados_invalidos")
        String erro,
        @Schema(description = "Mensagem principal do erro.", example = "A requisicao possui campos invalidos.")
        String mensagem,
        @Schema(description = "Detalhes adicionais por campo ou contexto do erro.")
        Map<String, String> detalhes
) {
}
