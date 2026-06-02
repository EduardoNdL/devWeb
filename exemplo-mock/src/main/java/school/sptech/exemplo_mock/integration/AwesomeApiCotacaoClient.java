package school.sptech.exemplo_mock.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import school.sptech.exemplo_mock.exception.ExternalServiceException;

@Component
public class AwesomeApiCotacaoClient implements CotacaoMoedaClient {

    private final RestClient restClient;

    public AwesomeApiCotacaoClient(@Value("${clients.awesome-api.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public Double buscarCotacaoDolarVenda() {
        try {
            CotacaoResponse response = restClient.get()
                    .uri("/USD-BRL")
                    .retrieve()
                    .body(CotacaoResponse.class);

            if (response == null || response.USDBRL() == null || response.USDBRL().bid() == null) {
                throw new ExternalServiceException("Nao foi possivel obter a cotacao atual do dolar.");
            }

            return Double.valueOf(response.USDBRL().bid());
        } catch (RestClientException ex) {
            throw new ExternalServiceException("Erro ao consultar a cotacao atual do dolar.", ex);
        }
    }

    private record CotacaoResponse(CotacaoDetalhe USDBRL) {
    }

    private record CotacaoDetalhe(String bid) {
    }
}
