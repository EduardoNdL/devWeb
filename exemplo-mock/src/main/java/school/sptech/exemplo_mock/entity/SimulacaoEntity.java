package school.sptech.exemplo_mock.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "simulacoes_compra_dolar")
public class SimulacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String descricao;

    @Column(nullable = false)
    private Double valorEmReais;

    @Column(nullable = false)
    private Double cotacaoDesejada;

    public SimulacaoEntity() {
    }

    public SimulacaoEntity(Long id, String descricao, Double valorEmReais, Double cotacaoDesejada) {
        this.id = id;
        this.descricao = descricao;
        this.valorEmReais = valorEmReais;
        this.cotacaoDesejada = cotacaoDesejada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorEmReais() {
        return valorEmReais;
    }

    public void setValorEmReais(Double valorEmReais) {
        this.valorEmReais = valorEmReais;
    }

    public Double getCotacaoDesejada() {
        return cotacaoDesejada;
    }

    public void setCotacaoDesejada(Double cotacaoDesejada) {
        this.cotacaoDesejada = cotacaoDesejada;
    }
}
