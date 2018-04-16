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
    private boolean status;
    
    @OneToOne
    private Agendamento agendamento;

    public Pesquisa() {
    }

    public Pesquisa(boolean status, Agendamento agendamento) {
        this.status = status;
        this.agendamento = agendamento;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public String toString() {
        return "Pesquisa{" + "id=" + id + ", tempoEspera=" + tempoEspera + ", qualidadeAtendimento=" + qualidadeAtendimento + ", ambiente=" + ambiente + ", qualidadeServico=" + qualidadeServico + ", status=" + status + ", agendamento=" + agendamento + '}';
    }
    
    
}
