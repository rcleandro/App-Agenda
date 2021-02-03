package com.example.ava2;

public class Contato {
    private long id;
    private String nome, telefone, email, aniversario;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if (!nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        if (!nome.isEmpty()) {
            this.telefone = telefone;
        }
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (!nome.isEmpty()) {
            this.email = email;
        }
    }

    public String getAniversario() {
        return aniversario;
    }
    public void setAniversario(String aniversario) {
        if (!nome.isEmpty()) {
            this.aniversario = aniversario;
        }
    }

    public Contato() {
        nome = "Nome";
        telefone = "Telefone";
        email = "E-Mail";
        aniversario = "Aniversário";
    }

    public String textoLista() {
        String item;
        item = getNome();
        item += getTelefone();
        item += getEmail();
        item += getAniversario();
        return item;
    }
}
