/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.model;

/**
 *
 * @author romulo
 */
public class Servico {
    
    private String nome;
    private int duracao;
    private double preco;

    public Servico() {
    }

    public Servico(String nome, int duracao, double preco) {
        this.nome = nome;
        this.duracao = duracao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
}
