package school.sptech.exemplo_mock.service;

import school.sptech.exemplo_mock.entity.SimulacaoEntity;

public class ResultadoSimulacao {

    private SimulacaoEntity simulacao;
    private Double cotacaoAtual;
    private Double quantidadeDolares;
    private Double diferencaCotacao;
    private String recomendacao;
    private String mensagem;

    public ResultadoSimulacao() {
    }

    public ResultadoSimulacao(
            SimulacaoEntity simulacao,
            Double cotacaoAtual,
            Double quantidadeDolares,
            Double diferencaCotacao,
            String recomendacao,
            String mensagem
    ) {
        this.simulacao = simulacao;
        this.cotacaoAtual = cotacaoAtual;
        this.quantidadeDolares = quantidadeDolares;
        this.diferencaCotacao = diferencaCotacao;
        this.recomendacao = recomendacao;
        this.mensagem = mensagem;
    }

    public SimulacaoEntity getSimulacao() {
        return simulacao;
    }

    public void setSimulacao(SimulacaoEntity simulacao) {
        this.simulacao = simulacao;
    }

    public Double getCotacaoAtual() {
        return cotacaoAtual;
    }

    public void setCotacaoAtual(Double cotacaoAtual) {
        this.cotacaoAtual = cotacaoAtual;
    }

    public Double getQuantidadeDolares() {
        return quantidadeDolares;
    }

    public void setQuantidadeDolares(Double quantidadeDolares) {
        this.quantidadeDolares = quantidadeDolares;
    }

    public Double getDiferencaCotacao() {
        return diferencaCotacao;
    }

    public void setDiferencaCotacao(Double diferencaCotacao) {
        this.diferencaCotacao = diferencaCotacao;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
