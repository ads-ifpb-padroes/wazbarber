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
public class Cliente {

    private String nomeCompleto;
    private String apelido;
    private String celular;
    private String cidade;

    public Cliente() {
    }

    public Cliente(String nomeCompleto, String apelido, String celular, String cidade) {
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.celular = celular;
        this.cidade = cidade;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nomeCompleto=" + nomeCompleto
                + ", apelido=" + apelido + ", celular=" + celular
                + ", cidade=" + cidade + '}';
    }

}
