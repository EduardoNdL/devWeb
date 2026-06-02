package school.sptech.exemplo_mock.service;

import org.springframework.stereotype.Service;
import school.sptech.exemplo_mock.exception.ResourceNotFoundException;
import school.sptech.exemplo_mock.entity.SimulacaoEntity;
import school.sptech.exemplo_mock.integration.CotacaoMoedaClient;
import school.sptech.exemplo_mock.repository.SimulacaoRepository;

import java.util.List;

@Service
public class SimulacaoService {

    private static final String STATUS_COMPRAR = "COMPRAR_AGORA";
    private static final String STATUS_AGUARDAR = "AGUARDAR";

    private final SimulacaoRepository repository;
    private final CotacaoMoedaClient cotacaoMoedaClient;

    public SimulacaoService(SimulacaoRepository repository, CotacaoMoedaClient cotacaoMoedaClient) {
        this.repository = repository;
        this.cotacaoMoedaClient = cotacaoMoedaClient;
    }

    public List<SimulacaoEntity> listar() {
        return repository.findAll();
    }

    public SimulacaoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Simulacao nao encontrada com id " + id + "."));
    }

    public SimulacaoEntity cadastrar(SimulacaoEntity simulacao) {
        return repository.save(simulacao);
    }

    public SimulacaoEntity atualizar(Long id, SimulacaoEntity simulacaoAtualizada) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Simulacao nao encontrada com id " + id + ".");
        }
        simulacaoAtualizada.setId(id);
        return repository.save(simulacaoAtualizada);
    }

    public void deletar(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Simulacao nao encontrada com id " + id + ".");
        }

        repository.deleteById(id);
    }

    public ResultadoSimulacao simular(Long id) {

        SimulacaoEntity simulacaoEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Simulacao nao encontrada com id " + id + "."));

        Double cotacaoAtual = cotacaoMoedaClient.buscarCotacaoDolarVenda();
        Double quantidadeDolares = simulacaoEntity.getValorEmReais() / cotacaoAtual;
        Double diferencaCotacao = cotacaoAtual - simulacaoEntity.getCotacaoDesejada();

        boolean podeComprar = cotacaoAtual <= simulacaoEntity.getCotacaoDesejada();

        String recomendacao = podeComprar ? STATUS_COMPRAR : STATUS_AGUARDAR;

        String mensagem = podeComprar
                ? "A cotacao atual ficou dentro do valor que voce queria pagar."
                : "A cotacao atual esta acima do valor desejado, entao vale esperar.";

        return new ResultadoSimulacao(
                simulacaoEntity,
                cotacaoAtual,
                quantidadeDolares,
                diferencaCotacao,
                recomendacao,
                mensagem
        );
    }
}
