package br.edu.ifpb.wazbarber.model;

import br.edu.ifpb.wazbarber.model.enums.TipoDeAtendente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author romulo
 */
@Entity
public class Atendente implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String nome;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;

    @Enumerated(EnumType.STRING)
    private TipoDeAtendente tipoDeAtendente;

//    @OneToMany
//    private List<Agendamento> agendamentos;

    @ManyToMany
    private List<Servico> servicos;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<HorarioAtendimento> horariosAtendimento;

    public Atendente() {

    }

    public Atendente(String nome, byte[] foto, TipoDeAtendente tipoDeAtendente) {
        this.nome = nome;
        this.foto = foto;
        this.tipoDeAtendente = tipoDeAtendente;
//        this.agendamentos = new ArrayList<>();
        this.servicos = new ArrayList<>();
        this.horariosAtendimento = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public TipoDeAtendente getTipoDeAtendente() {
        return tipoDeAtendente;
    }

    public void setTipoDeAtendente(TipoDeAtendente tipoDeAtendente) {
        this.tipoDeAtendente = tipoDeAtendente;
    }

//    public boolean addAgendamentos(Agendamento agendamento) {
//        return agendamentos.add(agendamento);
//    }

    public boolean addServicos(Servico servico) {
        return servicos.add(servico);
    }

//    public List<Agendamento> getAgendamentos() {
//        return agendamentos;
//    }

//    public void setAgendamentos(List<Agendamento> agendamentos) {
//        this.agendamentos = agendamentos;
//    }

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public List<HorarioAtendimento> getHorariosAtendimento() {
        return horariosAtendimento;
    }

    public void setHorariosAtendimento(List<HorarioAtendimento> horariosAtendimento) {
        this.horariosAtendimento = horariosAtendimento;
    }

    public boolean addHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
        return horariosAtendimento.add(horarioAtendimento);
    }

    @Override
    public String toString() {
        return "Atendente{" + "id=" + id + ", nome=" + nome
                + ", foto=" + foto + ", tipoDeAtendente=" + tipoDeAtendente + '}';
    }
}
