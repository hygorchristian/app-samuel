package com.samuca.contato.crud.models;

import android.widget.Adapter;

import java.io.Serializable;

/**
 * Created by samuca on 07/04/2018.
 */


public class Contato implements Serializable {

    public int id;
    private String fotoContato;
    public String nome;
    public String email;
    public String telefone;

    public Contato(int id,String fotoContato, String nome, String email, String telefone) {
        this.id = id;
        this.fotoContato = fotoContato;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Contato() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFotoContato() {
        return fotoContato;
    }

    public void setFotoContato(String fotoContato) {
        this.fotoContato = fotoContato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
