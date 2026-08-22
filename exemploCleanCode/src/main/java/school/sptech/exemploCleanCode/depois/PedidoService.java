package school.sptech.exemploCleanCode.depois;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exemploCleanCode.dto.PedidoRequest;
import school.sptech.exemploCleanCode.dto.PedidoResponse;

import java.math.BigDecimal;

@Service
public class PedidoService {
    public static final BigDecimal DESCONTO_CLIENTE_VIP =
            BigDecimal.valueOf(0.10);

    public PedidoResponse calcularTotal(PedidoRequest pedido){
        validarPedido(pedido);
        BigDecimal total = pedido.precoUnitario()
                .multiply(BigDecimal.valueOf(pedido.quantidade()));
        if(pedido.clienteVip()) {
            BigDecimal desconto = total.multiply(DESCONTO_CLIENTE_VIP);
        }

        return new PedidoResponse(pedido.produto(),
                total, "Pedido criado com Sucesso");
    }

    private void validarPedido(PedidoRequest pedido) {
        if(pedido.produto() == null || pedido.produto().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Produto Inválido"
            );
        }

        if(pedido.quantidade() == null || pedido.quantidade() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantidade Inválida"
            );
        }

        if(pedido.precoUnitario() == null || pedido.precoUnitario().compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Preço Inválido"
            );
        }
    }
}