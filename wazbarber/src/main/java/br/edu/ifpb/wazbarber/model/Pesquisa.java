package br.edu.ifpb.wazbarber.model;

import br.edu.ifpb.wazbarber.conversores.LocalDateConverter;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Convert;
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
    private String email;
    
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dataAtendimento;
    @OneToOne
    private Atendente atendente;

    public Pesquisa() {
    }

    public Pesquisa(int tempoEspera, int qualidadeAtendimento, int ambiente, int qualidadeServico, boolean status, String email, Atendente atendente) {
        this.tempoEspera = tempoEspera;
        this.qualidadeAtendimento = qualidadeAtendimento;
        this.ambiente = ambiente;
        this.qualidadeServico = qualidadeServico;
        this.status = status;
        this.email = email;
        this.atendente = atendente;
    }

    public Pesquisa(boolean status, String email, Atendente atendente, LocalDate dataAtendimento) {
        this.status = status;
        this.email = email;
        this.atendente = atendente;
        this.dataAtendimento = dataAtendimento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    @Override
    public String toString() {
        return "Pesquisa{" + "id=" + id + ", tempoEspera=" + tempoEspera + ", qualidadeAtendimento=" + qualidadeAtendimento + ", ambiente=" + ambiente + ", qualidadeServico=" + qualidadeServico + ", status=" + status + ", email=" + email + ", dataAtendimento=" + dataAtendimento + ", atendente=" + atendente + '}';
    }

    
    
    
    
    
}
