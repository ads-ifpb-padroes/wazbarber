package br.edu.ifpb.wazbarber.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author romulo
 */
@Entity
public class Pesquisa implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private int tempoEspera;
    private int qualidadeAtendimento;
    private int ambiente;
    private int qualidadeServico;

    @OneToOne
    private Atendente atendente;

    public Pesquisa() {
    }

    public Pesquisa(int tempoEspera, int qualidadeAtendimento, int ambiente, int qualidadeServico) {
        this.tempoEspera = tempoEspera;
        this.qualidadeAtendimento = qualidadeAtendimento;
        this.ambiente = ambiente;
        this.qualidadeServico = qualidadeServico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(int tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public int getQualidadeAtendimento() {
        return qualidadeAtendimento;
    }

    public void setQualidadeAtendimento(int qualidadeAtendimento) {
        this.qualidadeAtendimento = qualidadeAtendimento;
    }

    public int getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(int ambiente) {
        this.ambiente = ambiente;
    }

    public int getQualidadeServico() {
        return qualidadeServico;
    }

    public void setQualidadeServico(int qualidadeServico) {
        this.qualidadeServico = qualidadeServico;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
}
