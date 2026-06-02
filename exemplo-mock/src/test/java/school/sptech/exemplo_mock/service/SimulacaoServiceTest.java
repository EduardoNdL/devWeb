package school.sptech.exemplo_mock.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.exemplo_mock.entity.SimulacaoEntity;
import school.sptech.exemplo_mock.exception.ResourceNotFoundException;
import school.sptech.exemplo_mock.integration.CotacaoMoedaClient;
import school.sptech.exemplo_mock.repository.SimulacaoRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulacaoServiceTest {

    @Mock
    private SimulacaoRepository simulacaoRepository;

    @Mock
    private CotacaoMoedaClient cotacaoMoedaClient;

    @InjectMocks
    private SimulacaoService simulacaoService;

    @Nested
    @DisplayName("GET /simulacao")
    class listar {
        @Test
        void shouldReturnTrueWhenReturnIsAnEmptyList() {
            var listaVazia = Collections.EMPTY_LIST;

            when(simulacaoRepository.findAll()).thenReturn(List.of());

            List<SimulacaoEntity> response = simulacaoService.listar();

            Assertions.assertTrue(response.isEmpty());
        }

        @Test
        void shouldReturnFalseWhenReturnIsANotEmptyList(){
            var lista = List.of(new SimulacaoEntity(), new SimulacaoEntity());

            when(simulacaoRepository.findAll()).thenReturn(lista);

            List<SimulacaoEntity> response = simulacaoService.listar();

            Assertions.assertFalse(response.isEmpty());
        }
    }

    @Nested
    @DisplayName("GET /id")
    class buscarPorid {

        @Test
        @DisplayName("Should Return A SimulacaoEntity Entity When Resource Is Found")
        void shouldReturnASimulacaoEntityWhenAResourceIsFound(){
            SimulacaoEntity example = new SimulacaoEntity();

            when(simulacaoRepository.findById(1L)).thenReturn(Optional.of(example));

            Assertions.assertEquals(example, simulacaoService.buscarPorId(1L));
        }

        @Test
        @DisplayName("Should Throw An Exception When Resource Is Not Found")
        void shouldThrowAnExceptionWhenAResourceIsNotFound(){
            SimulacaoEntity example = new SimulacaoEntity();

            when(simulacaoRepository.findById(1L)).thenReturn(Optional.empty());

            Assertions.assertThrows(ResourceNotFoundException.class,
                    () -> simulacaoService.buscarPorId(1L));
        }
    }

    @Nested
    @DisplayName("DELETE /id")
    class delete {
        @Test
        @DisplayName("Should Throw An Exception When Resource Is Not Found")
        void shouldThrowAnExceptionWhenAResourceIsNotFound(){
            when(simulacaoRepository.existsById(anyLong())).thenReturn(false);

            Assertions.assertThrows(ResourceNotFoundException.class,
                    () -> simulacaoService.deletar(anyLong()));
        }

        @Test
        @DisplayName("")
        void shouldReturnVOidWhenResourceIsDeleted(){
            when(simulacaoRepository.existsById(anyLong())).thenReturn(true);

            simulacaoService.deletar(anyLong());

            verify(simulacaoRepository, times(1)).deleteById(anyLong());
        }
    }

    @Nested
    @DisplayName("Simulation")
    class simulate {
        @Test
        void shouldReturnRightMessageWhenItsPossibleToBuy(){
            SimulacaoEntity simulacaoEntity = new SimulacaoEntity();

            simulacaoEntity.setValorEmReais(25.0);
            simulacaoEntity.setCotacaoDesejada(4.8);
            when(simulacaoRepository.findById(anyLong())).thenReturn(Optional.of(simulacaoEntity));

            when(cotacaoMoedaClient.buscarCotacaoDolarVenda()).thenReturn(4.5);

            ResultadoSimulacao resultadoSimulacao = simulacaoService.simular(anyLong());

            Assertions.assertEquals("A cotacao atual ficou dentro do valor que voce queria pagar.",
                    resultadoSimulacao.getMensagem());
        }

        @Test
        void shouldReturnRightMessageWhenItsImpossibleToBuy(){
            SimulacaoEntity simulacaoEntity = new SimulacaoEntity();

            simulacaoEntity.setValorEmReais(25.0);
            simulacaoEntity.setCotacaoDesejada(4.8);
            when(simulacaoRepository.findById(anyLong())).thenReturn(Optional.of(simulacaoEntity));

            when(cotacaoMoedaClient.buscarCotacaoDolarVenda()).thenReturn(5.2);

            ResultadoSimulacao resultadoSimulacao = simulacaoService.simular(anyLong());

            Assertions.assertEquals("A cotacao atual esta acima do valor desejado, entao vale esperar.",
                    resultadoSimulacao.getMensagem());
        }
    }
}