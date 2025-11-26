package com.example.projeto_2.classes;

public class Musico {
    private int id;
    private String cpf;
    private String nome;
    private String genero;
    private String instrumentoQueToca;
    private String celular;
    private String email;
    private String endereco;

    // Construtores
    public Musico() {
        id = 0;
        cpf = "";
        nome = "";
        genero = "";
        instrumentoQueToca = "";
        celular = "";
        email = "";
        endereco = "";
    }

    public Musico(int id, String cpf, String nome, String genero, String instrumentoQueToca, String celular, String email, String endereco) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.genero = genero;
        this.instrumentoQueToca = instrumentoQueToca;
        this.celular = celular;
        this.email = email;
        this.endereco = endereco;
    }


    // Getters n Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getInstrumentoQueToca() {
        return instrumentoQueToca;
    }

    public void setInstrumentoQueToca(String instrumentoQueToca) {
        this.instrumentoQueToca = instrumentoQueToca;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
