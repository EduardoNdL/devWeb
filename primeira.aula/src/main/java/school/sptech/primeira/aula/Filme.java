package school.sptech.primeira.aula;

public class Filme {
    private String nome;
    private String diretor;

    public Filme(String nome, String diretor) {
        this.nome = nome;
        this.diretor = diretor;
    }

    // Campo virtual ou Campo calculado
    public String getInfo(){
        return nome + " " + diretor;
    }

    public String getNome() {
        return nome;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
}
