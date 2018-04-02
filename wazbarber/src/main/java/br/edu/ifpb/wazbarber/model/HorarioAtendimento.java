package br.edu.ifpb.wazbarber.model;

import br.edu.ifpb.wazbarber.util.LocalTimeConverter;
import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author romulo
 */
@Entity
public class HorarioAtendimento implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Convert(converter = LocalTimeConverter.class)
    private LocalTime horaChegada;

    @Convert(converter = LocalTimeConverter.class)
    private LocalTime horaSaida;

    @Enumerated(EnumType.STRING)
    private Dia dia;

    public HorarioAtendimento() {
    }

    public HorarioAtendimento(LocalTime horaChegada, LocalTime horaSaida) {
        this.horaChegada = horaChegada;
        this.horaSaida = horaSaida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }
}
