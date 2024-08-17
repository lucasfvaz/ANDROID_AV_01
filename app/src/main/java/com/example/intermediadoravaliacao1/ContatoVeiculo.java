package com.example.intermediadoravaliacao1;

import java.io.Serializable;

public class ContatoVeiculo implements Serializable {
    private String nome, email;
    private String telefone;
    private double valor;
    private String marca;
    private String modelo ;

    private String tipo;
    private int ano;

    private String vendendocomprando;

    public ContatoVeiculo(String nome, String email, String telefone, double valor, String marca, String modelo, String tipo, int ano, String vendendocomprando) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.valor = valor;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.ano = ano;
        this.vendendocomprando = vendendocomprando;
    }

    public ContatoVeiculo() {
      
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getVendendocomprando() {
        return vendendocomprando;
    }

    public void setVendendocomprando(String vendendocomprando) {
        this.vendendocomprando = vendendocomprando;
    }

    @Override
    public String toString() {
        return "nome='" + nome +", valor=" + valor + ", marca='" + marca;
    }
}
