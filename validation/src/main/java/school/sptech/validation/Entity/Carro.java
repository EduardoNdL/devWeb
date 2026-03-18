package school.sptech.validation.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelo;
    private String montadora;
    private String cor;
    private Double preco;
    private Boolean eletrico;
    private Integer cavalos;
    private LocalDate anoFabricacao;

    public Carro() {
    }

    public Carro(Integer id, String modelo, String montadora, String cor, Double preco, Boolean eletrico, Integer cavalos, LocalDate anoFabricacao) {
        this.id = id;
        this.modelo = modelo;
        this.montadora = montadora;
        this.cor = cor;
        this.preco = preco;
        this.eletrico = eletrico;
        this.cavalos = cavalos;
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
