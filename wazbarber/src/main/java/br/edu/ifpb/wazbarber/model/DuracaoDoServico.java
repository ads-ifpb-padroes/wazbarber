package br.edu.ifpb.wazbarber.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author jozimar
 */
@Entity
public class DuracaoDoServico implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    
    private int duracao;

    @OneToOne
    private Atendente atendente;

    @OneToOne
    private Servico servico;

    public DuracaoDoServico() {
    }

    public DuracaoDoServico(int duracao) {
        this.duracao = duracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
