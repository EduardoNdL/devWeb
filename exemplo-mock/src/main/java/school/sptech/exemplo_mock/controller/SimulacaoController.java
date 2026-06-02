package school.sptech.exemplo_mock.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.sptech.exemplo_mock.exception.handler.ApiErrorResponse;
import school.sptech.exemplo_mock.dto.SimulacaoAtualizacaoDto;
import school.sptech.exemplo_mock.dto.SimulacaoCadastroDto;
import school.sptech.exemplo_mock.dto.SimulacaoResponseDto;
import school.sptech.exemplo_mock.dto.SimulacaoResultadoDto;
import school.sptech.exemplo_mock.entity.SimulacaoEntity;
import school.sptech.exemplo_mock.mapper.SimulacaoMapper;
import school.sptech.exemplo_mock.service.ResultadoSimulacao;
import school.sptech.exemplo_mock.service.SimulacaoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/simulacoes-compra-dolar")
@Tag(
        name = "Simulacoes de compra de dolar",
        description = "CRUD de simulacoes simples de compra de dolar com cotacao externa."
)
public class SimulacaoController {

    private final SimulacaoService service;

    public SimulacaoController(SimulacaoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar simulacoes", description = "Retorna todas as simulacoes cadastradas.")
    @ApiResponse(
            responseCode = "200",
            description = "Simulacoes encontradas",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = SimulacaoResponseDto.class)))
    )
    public ResponseEntity<List<SimulacaoResponseDto>> listar() {
        List<SimulacaoResponseDto> response = service.listar().stream()
                .map(SimulacaoMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar simulacao por id", description = "Retorna uma simulacao especifica.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Simulacao encontrada",
                    content = @Content(schema = @Schema(implementation = SimulacaoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Simulacao nao encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<SimulacaoResponseDto> buscarPorId(@PathVariable Long id) {
        SimulacaoEntity simulacao = service.buscarPorId(id);
        return ResponseEntity.ok(SimulacaoMapper.toResponseDto(simulacao));
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar simulacao",
            description = "Cria uma simulacao informando descricao, valor em reais disponivel e cotacao desejada.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de cadastro",
                                    value = """
                                            {
                                              "descricao": "Compra para viagem",
                                              "valorEmReais": 1500.0,
                                              "cotacaoDesejada": 5.20
                                            }"""
                            )
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Simulacao criada",
                    content = @Content(schema = @Schema(implementation = SimulacaoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<SimulacaoResponseDto> cadastrar(
            @RequestBody @Valid SimulacaoCadastroDto dto
    ) {
        SimulacaoEntity simulacao = service.cadastrar(SimulacaoMapper.toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(simulacao.getId())
                .toUri();

        return ResponseEntity.created(location)
                .body(SimulacaoMapper.toResponseDto(simulacao));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar simulacao",
            description = "Atualiza todos os dados de uma simulacao existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de atualizacao",
                                    value = """
                                            {
                                              "descricao": "Compra para intercambio",
                                              "valorEmReais": 2300.0,
                                              "cotacaoDesejada": 5.10
                                            }"""
                            )
                    )
            )
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Simulacao atualizada",
                    content = @Content(schema = @Schema(implementation = SimulacaoResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados invalidos",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Simulacao nao encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<SimulacaoResponseDto> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SimulacaoAtualizacaoDto dto
    ) {
        SimulacaoEntity simulacao = service.atualizar(id, SimulacaoMapper.toEntity(dto));
        return ResponseEntity.ok(SimulacaoMapper.toResponseDto(simulacao));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover simulacao", description = "Exclui uma simulacao pelo identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Simulacao removida"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Simulacao nao encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/simulacao")
    @Operation(
            summary = "Executar simulacao",
            description = "Consulta a cotacao atual do dolar, calcula quantos dolares seria possivel comprar e informa se vale comprar agora."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Simulacao realizada",
                    content = @Content(schema = @Schema(implementation = SimulacaoResultadoDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Simulacao nao encontrada",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Falha ao consultar cotacao externa",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<SimulacaoResultadoDto> simular(@PathVariable Long id) {
        ResultadoSimulacao resultado = service.simular(id);
        return ResponseEntity.ok(SimulacaoMapper.toResultadoDto(resultado));
    }
}
