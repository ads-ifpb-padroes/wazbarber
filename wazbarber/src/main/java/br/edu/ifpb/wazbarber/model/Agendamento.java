package br.edu.ifpb.wazbarber.model;

import br.edu.ifpb.wazbarber.conversores.LocalDateConverter;
import br.edu.ifpb.wazbarber.conversores.LocalDateTimeConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author romulo
 */
@Entity
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime horario;

    @Convert(converter = LocalDateConverter.class)
    private LocalDate data;

    private boolean confirmado;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Atendente atendente;

    @OneToOne
    private Servico servico;

    public Agendamento() {
    }

    public Agendamento(int id, LocalDateTime horario, 
            LocalDate data, boolean confirmado) {
        this.id = id;
        this.horario = horario;
        this.data = data;
        this.confirmado = confirmado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
