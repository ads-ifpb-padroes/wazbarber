package br.edu.ifpb.wazbarber.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author romulo
 */
@Entity
public class Servico implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String nome;
    private int duracao;
    private double preco;

//    @ManyToMany(mappedBy = "servicos")
    @ManyToMany
    private List<Atendente> atendentes;

    public Servico() {
    }

    public Servico(String nome, int duracao, double preco) {
        this.nome = nome;
        this.duracao = duracao;
        this.preco = preco;
        this.atendentes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean addAtendentes(Atendente atendente) {
        return atendentes.add(atendente);
    }
}
