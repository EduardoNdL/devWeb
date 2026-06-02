package school.sptech.exemplo_mock.mapper;

import school.sptech.exemplo_mock.dto.SimulacaoAtualizacaoDto;
import school.sptech.exemplo_mock.dto.SimulacaoCadastroDto;
import school.sptech.exemplo_mock.dto.SimulacaoResponseDto;
import school.sptech.exemplo_mock.dto.SimulacaoResultadoDto;
import school.sptech.exemplo_mock.entity.SimulacaoEntity;
import school.sptech.exemplo_mock.service.ResultadoSimulacao;

public final class SimulacaoMapper {

    private SimulacaoMapper() {
    }

    public static SimulacaoEntity toEntity(SimulacaoCadastroDto dto) {
        SimulacaoEntity entity = new SimulacaoEntity();
        entity.setDescricao(dto.descricao());
        entity.setValorEmReais(dto.valorEmReais());
        entity.setCotacaoDesejada(dto.cotacaoDesejada());
        return entity;
    }

    public static SimulacaoEntity toEntity(SimulacaoAtualizacaoDto dto) {
        SimulacaoEntity entity = new SimulacaoEntity();
        entity.setDescricao(dto.descricao());
        entity.setValorEmReais(dto.valorEmReais());
        entity.setCotacaoDesejada(dto.cotacaoDesejada());
        return entity;
    }

    public static SimulacaoResponseDto toResponseDto(SimulacaoEntity entity) {
        return new SimulacaoResponseDto(
                entity.getId(),
                entity.getDescricao(),
                entity.getValorEmReais(),
                entity.getCotacaoDesejada()
        );
    }

    public static SimulacaoResultadoDto toResultadoDto(ResultadoSimulacao resultado) {
        return new SimulacaoResultadoDto(
                resultado.getSimulacao().getId(),
                resultado.getSimulacao().getDescricao(),
                resultado.getSimulacao().getValorEmReais(),
                resultado.getSimulacao().getCotacaoDesejada(),
                resultado.getCotacaoAtual(),
                resultado.getQuantidadeDolares(),
                resultado.getDiferencaCotacao(),
                resultado.getRecomendacao(),
                resultado.getMensagem()
        );
    }
}
