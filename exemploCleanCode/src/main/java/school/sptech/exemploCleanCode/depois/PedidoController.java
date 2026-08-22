package school.sptech.exemploCleanCode.depois;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.exemploCleanCode.dto.PedidoRequest;
import school.sptech.exemploCleanCode.dto.PedidoResponse;

@RestController
@RequestMapping("/depois/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public PedidoResponse criarPedido(@RequestBody PedidoRequest pedidoRequest){
        return pedidoService.calcularTotal(pedido);
    }
}
