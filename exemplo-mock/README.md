# API de simulacao de compra de dolar

O dominio e **simulacao de compra de dolar**: o usuario cadastra quanto tem disponivel em reais e qual cotacao considera aceitavel. A API consulta a cotacao atual em uma API externa e diz se vale comprar agora ou esperar.

## O que cada simulacao guarda

| Campo | Tipo | Descricao |
|---|---|---|
| `descricao` | String | Identificacao da simulacao |
| `valorEmReais` | Double | Quanto a pessoa tem disponivel para comprar |
| `cotacaoDesejada` | Double | Cotacao maxima que ela considera aceitavel |

## Regra de negocio

O endpoint de simulacao chama a AwesomeAPI para obter a cotacao atual USD-BRL e calcula:

- `cotacaoAtual` — valor retornado pela API externa
- `quantidadeDolares` — `valorEmReais / cotacaoAtual`
- `diferencaCotacao` — `cotacaoAtual - cotacaoDesejada`
- `recomendacao` — decisao sobre comprar ou nao
- `mensagem` — explicacao em texto da recomendacao

### Logica da recomendacao

| Situacao | `recomendacao` |
|---|---|
| `cotacaoAtual <= cotacaoDesejada` | `COMPRAR_AGORA` |
| `cotacaoAtual > cotacaoDesejada` | `AGUARDAR` |

## API externa consumida

A cotacao do dolar e consultada na **AwesomeAPI**:

- Documentacao: https://docs.awesomeapi.com.br/api-de-moedas
- Endpoint utilizado: `GET https://economia.awesomeapi.com.br/json/last/USD-BRL`

Exemplo de resposta da AwesomeAPI:

```json
{
  "USDBRL": {
    "bid": "5.0823"
  }
}
```

## Arquitetura

```
Controller  →  Mapper  →  Service  →  Repository
                               ↓
                       CotacaoMoedaClient (API externa)
                               ↓
                       ResultadoSimulacao (record)
                               ↓
               Mapper  →  SimulacaoCompraDolarResultadoDto
```

- **Entity**: apenas campos persistidos no banco (sem campos calculados)
- **ResultadoSimulacao**: record com o resultado do calculo da simulacao (nao persistido)
- **Mapper**: converte entre DTO, Entity e ResultadoSimulacao
- **CotacaoMoedaClient**: interface que abstrai a chamada externa (facilita mock nos testes)

## Endpoints

| Metodo | Endpoint | Descricao |
|---|---|---|
| `GET` | `/simulacoes-compra-dolar` | Lista todas as simulacoes |
| `GET` | `/simulacoes-compra-dolar/{id}` | Busca uma simulacao por id |
| `POST` | `/simulacoes-compra-dolar` | Cria uma nova simulacao |
| `PUT` | `/simulacoes-compra-dolar/{id}` | Atualiza uma simulacao |
| `DELETE` | `/simulacoes-compra-dolar/{id}` | Remove uma simulacao |
| `GET` | `/simulacoes-compra-dolar/{id}/simulacao` | Executa a simulacao com cotacao atual |

## Exemplos de uso com cURL

**Criar uma simulacao:**

```bash
curl -X POST http://localhost:8080/simulacoes-compra-dolar \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Compra para viagem",
    "valorEmReais": 1500.0,
    "cotacaoDesejada": 5.20
  }'
```

**Executar a simulacao (consulta cotacao atual):**

```bash
curl http://localhost:8080/simulacoes-compra-dolar/1/simulacao
```

**Exemplo de resposta da simulacao:**

```json
{
  "id": 1,
  "descricao": "Compra para viagem",
  "valorEmReais": 1500.0,
  "cotacaoDesejada": 5.20,
  "cotacaoAtual": 5.08,
  "quantidadeDolares": 295.28,
  "diferencaCotacao": -0.12,
  "recomendacao": "COMPRAR_AGORA",
  "mensagem": "A cotacao atual ficou dentro do valor que voce queria pagar."
}
```

## Como executar

```bash
./mvnw spring-boot:run
```

## Swagger

Com a aplicacao em execucao:

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Console H2: http://localhost:8080/h2-console
