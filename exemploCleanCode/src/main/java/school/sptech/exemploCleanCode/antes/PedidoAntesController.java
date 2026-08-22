package school.sptech.exemploCleanCode.antes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.exemploCleanCode.dto.PedidoRequest;
import school.sptech.exemploCleanCode.dto.PedidoResponse;

import java.math.BigDecimal;

@RestController
@RequestMapping("/antes/pedidos")
public class PedidoAntesController {
    @PostMapping
    public PedidoResponse x(@RequestBody PedidoRequest p) {
        if(p.produto() == null || p.produto().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Produto Inválido"
            );
        }

        if(p.quantidade() == null || p.quantidade() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantidade Inválida"
            );
        }

        if(p.precoUnitario() == null || p.precoUnitario().compareTo(BigDecimal.ZERO) <= 0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Preço Inválido"
            );
        }

        BigDecimal t = p.precoUnitario().multiply(
                BigDecimal.valueOf(p.quantidade()));

        if(p.clienteVip() == true){
            BigDecimal d = t.multiply(BigDecimal.valueOf(0.10));
            t=t.subtract(d);
        } else{
            t=t;
        }

        return new PedidoResponse(
                p.produto(),
                t,
                "Pedido calculado com sucesso"
        )
    }
}
