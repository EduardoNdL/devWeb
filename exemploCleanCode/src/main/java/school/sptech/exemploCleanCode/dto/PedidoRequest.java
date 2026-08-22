package school.sptech.exemploCleanCode.dto;

import java.math.BigDecimal;

public record PedidoRequest(
        String produto,
        Integer quantidade,
        BigDecimal precoUnitario,
        boolean clienteVip
) {

}
