package school.sptech.exemploCleanCode.dto;

import java.math.BigDecimal;

public record PedidoResponse(
        String produto,
        BigDecimal total,
        String mensagem
) {
}
