package school.sptech.exemplo_jpa;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class Heroi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Integer forca;
    private String arma;
    private Boolean temCapa;

    public Heroi() {
    }

    public Heroi(HeroiRequestDto heroi) {
        this.nome = heroi.getNome();
        this.forca = heroi.getForca();
        this.arma = heroi.getArma();
        this.temCapa = heroi.getTemCapa();
    }

    public Heroi(Integer id, String nome, Integer forca, String arma, Boolean temCapa) {
        this.id = id;
        this.nome = nome;
        this.forca = forca;
        this.arma = arma;
        this.temCapa = temCapa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public Boolean getTemCapa() {
        return temCapa;
    }

    public void setTemCapa(Boolean temCapa) {
        this.temCapa = temCapa;
    }
}
