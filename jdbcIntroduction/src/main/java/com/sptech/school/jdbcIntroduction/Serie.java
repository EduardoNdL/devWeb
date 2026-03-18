package com.sptech.school.jdbcIntroduction;

import java.util.UUID;

public class Serie {
    private UUID id;
    private String nome;
    private String plataforma;
    private int nota;

    public Serie() {
    }

    public Serie(UUID id, String nome, String plataforma, int nota) {
        this.id = id;
        this.nome = nome;
        this.plataforma = plataforma;
        this.nota = nota;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
