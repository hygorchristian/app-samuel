package com.samuca.contato.crud.models;

import java.io.Serializable;

/**
 * Created by samuca on 04/05/2018.
 */

public class Cidade implements Serializable {
    private int id;
    private String nomeCidade;



    public Cidade(int id, String nomeCidade) {
        this.id = id;
        this.nomeCidade = nomeCidade;
    }

    public Cidade() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nomeCidade='" + nomeCidade + '\'' +
                '}';
    }
}
