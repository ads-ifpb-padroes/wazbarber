/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.model.Agendamento;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author romulo
 */
@Named
@RequestScoped
public class ControladorDeAgendamentoHorario implements Serializable {

    private String opcaoAgendamento;
    private Agendamento agendamento;
    
    private String teste;
    
    //Método auxiliar temporário
    public String confirmar() {
        return null;
    }
    
    public ControladorDeAgendamentoHorario(){
        this.agendamento = new Agendamento();
    }
    
    public String confirmarAtendente() {
        System.out.println("Data AGENDAMENTO: " + teste);
        return null;
    }

    public String getOpcaoAgendamento() {
        return opcaoAgendamento;
    }

    public void setOpcaoAgendamento(String opcaoAgendamento) {
        this.opcaoAgendamento = opcaoAgendamento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }
    
}
