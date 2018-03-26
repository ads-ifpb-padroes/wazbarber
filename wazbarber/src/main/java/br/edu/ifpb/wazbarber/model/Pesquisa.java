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
public class Pesquisa {
 
    private int tempoEspera;
    private int qualidadeAtendimento;
    private int ambiente;
    private int qualidadeServico;

    public Pesquisa() {
    }

    public Pesquisa(int tempoEspera, int qualidadeAtendimento, int ambiente, int qualidadeServico) {
        this.tempoEspera = tempoEspera;
        this.qualidadeAtendimento = qualidadeAtendimento;
        this.ambiente = ambiente;
        this.qualidadeServico = qualidadeServico;
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
    
    
}
