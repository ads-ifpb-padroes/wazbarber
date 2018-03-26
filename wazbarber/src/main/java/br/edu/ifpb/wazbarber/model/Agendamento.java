/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author romulo
 */
public class Agendamento {
    
    private LocalDateTime horario;
    private LocalDate data;
    private boolean confirmado;

    public Agendamento() {
    }

    public Agendamento(LocalDateTime horario, LocalDate data, boolean confirmado) {
        this.horario = horario;
        this.data = data;
        this.confirmado = confirmado;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
    
}
