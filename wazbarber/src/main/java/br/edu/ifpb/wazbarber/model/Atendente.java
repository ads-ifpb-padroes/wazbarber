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
public class Atendente {
 
    private String nome;
    private byte[] foto;
    private String tipo; //Este atributo deve ser de um tipo ENUM. Cria um enum 
                         //com os tipos de manicure, barbeiro e cabeleleiro. Como
                         //está descrito na especificação. Segue o esquema do 
                         //enum da classe Dia. Na UML versao antes da 1.1 tem a
                         //modelagem, olha lá.

    public Atendente() {
    }

    public Atendente(String nome, byte[] foto, String tipo) {
        this.nome = nome;
        this.foto = foto;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
