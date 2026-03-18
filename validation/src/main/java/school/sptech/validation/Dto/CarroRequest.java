package school.sptech.validation.Dto;

import jakarta.validation.constraints.*;
import school.sptech.validation.Entity.Carro;

import java.time.LocalDate;

public class CarroRequest {
    @Size(min = 2, max = 20)
    @NotBlank
    private String modelo;
    private String montadora;
    private String cor;
    @Positive
    @DecimalMin(value = "50.0")
    @DecimalMax(value = "80000.0")
    private Double preco;

    @AssertFalse
    private Boolean eletrico;

    @Min(80)
    @Max(800)
    private Integer cavalos;

    @PastOrPresentt
    private LocalDate anoFabricacao;

    public CarroRequest() {
    }

    public CarroRequest( String modelo, String montadora, String cor, Double preco, Boolean eletrico, Integer cavalos, LocalDate anoFabricacao) {
        this.modelo = modelo;
        this.montadora = montadora;
        this.cor = cor;
        this.preco = preco;
        this.eletrico = eletrico;
        this.cavalos = cavalos;
        this.anoFabricacao = anoFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMontadora() {
        return montadora;
    }

    public void setMontadora(String montadora) {
        this.montadora = montadora;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getEletrico() {
        return eletrico;
    }

    public void setEletrico(Boolean eletrico) {
        this.eletrico = eletrico;
    }

    public Integer getCavalos() {
        return cavalos;
    }

    public void setCavalos(Integer cavalos) {
        this.cavalos = cavalos;
    }

    public LocalDate getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(LocalDate anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
}
