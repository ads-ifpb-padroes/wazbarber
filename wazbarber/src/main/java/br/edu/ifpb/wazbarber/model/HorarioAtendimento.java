/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.model;

import java.time.LocalTime;

/**
 *
 * @author romulo
 */
public class HorarioAtendimento {

    private LocalTime horaChegada;
    private LocalTime horaSaida;

    public HorarioAtendimento() {
    }

    public HorarioAtendimento(LocalTime horaChegada, LocalTime horaSaida) {
        this.horaChegada = horaChegada;
        this.horaSaida = horaSaida;
    }

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalTime horaChegada) {
        this.horaChegada = horaChegada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

}
