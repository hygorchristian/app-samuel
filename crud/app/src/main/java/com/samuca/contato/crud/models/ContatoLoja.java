package com.samuca.contato.crud.models;


import java.io.Serializable;
@SuppressWarnings("ContatoLoja")
public class ContatoLoja implements Serializable {

    private int id;
    private String nome_loja;
    private String foto_loja;
    private String telefone_loja;
    private String email_loja;
    private String whatsapp_loja;
    private String fecebook_loja;
    private String instagran_loja;
    private String endereco_loja;
    private String cidade_loja;
    private String categoria_loja;
    private String descricao_loja;

    public ContatoLoja(int id, String nome_loja, String foto_loja, String telefone_loja, String email_loja, String whatsapp_loja, String fecebook_loja, String instagran_loja, String endereco_loja, String cidade_loja, String categoria_loja, String descricao_loja) {
        this.id = id;
        this.nome_loja = nome_loja;
        this.foto_loja = foto_loja;
        this.telefone_loja = telefone_loja;
        this.email_loja = email_loja;
        this.whatsapp_loja = whatsapp_loja;
        this.fecebook_loja = fecebook_loja;
        this.instagran_loja = instagran_loja;
        this.endereco_loja = endereco_loja;
        this.cidade_loja = cidade_loja;
        this.categoria_loja = categoria_loja;
        this.descricao_loja = descricao_loja;
    }

    public ContatoLoja() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_loja() {
        return nome_loja;
    }

    public void setNome_loja(String nome_loja) {
        this.nome_loja = nome_loja;
    }

    public String getFoto_loja() {
        return foto_loja;
    }

    public void setFoto_loja(String foto_loja) {
        this.foto_loja = foto_loja;
    }

    public String getTelefone_loja() {
        return telefone_loja;
    }

    public void setTelefone_loja(String telefone_loja) {
        this.telefone_loja = telefone_loja;
    }

    public String getEmail_loja() {
        return email_loja;
    }

    public void setEmail_loja(String email_loja) {
        this.email_loja = email_loja;
    }

    public String getWhatsapp_loja() {
        return whatsapp_loja;
    }

    public void setWhatsapp_loja(String whatsapp_loja) {
        this.whatsapp_loja = whatsapp_loja;
    }

    public String getFecebook_loja() {
        return fecebook_loja;
    }

    public void setFecebook_loja(String fecebook_loja) {
        this.fecebook_loja = fecebook_loja;
    }

    public String getInstagran_loja() {
        return instagran_loja;
    }

    public void setInstagran_loja(String instagran_loja) {
        this.instagran_loja = instagran_loja;
    }

    public String getEndereco_loja() {
        return endereco_loja;
    }

    public void setEndereco_loja(String endereco_loja) {
        this.endereco_loja = endereco_loja;
    }

    public String getCidade_loja() {
        return cidade_loja;
    }

    public void setCidade_loja(String cidade_loja) {
        this.cidade_loja = cidade_loja;
    }

    public String getCategoria_loja() {
        return categoria_loja;
    }

    public void setCategoria_loja(String categoria_loja) {
        this.categoria_loja = categoria_loja;
    }

    public String getDescricao_loja() {
        return descricao_loja;
    }

    public void setDescricao_loja(String descricao_loja) {
        this.descricao_loja = descricao_loja;
    }
}
