/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.AgendamentoHorarioDao;
import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.interfaces.DaoServico;
import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.Cliente;
import br.edu.ifpb.wazbarber.model.Servico;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author romulo
 */
@Named
@SessionScoped
public class ControladorDeAgendamentoPorServico implements Serializable {

    private Agendamento agendamento;
    private String horarioAgendamento;
    private LocalDate dataAgendamento;
    private Atendente atendente;
    private Servico servico;
    private HttpSession sessao;

    @Inject
    private AgendamentoHorarioDao agendamentoHorarioDao;
    @Inject
    private DaoServico servicoDao;
    @Inject
    private DaoAtendente atendenteDao;

    @PostConstruct
    public void instanceObjects() {
        this.agendamento = new Agendamento();
        this.atendente = new Atendente();
        this.servico = new Servico();
        this.sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
    }

    public String confirmarAtendente() {
        atendente = atendenteDao.buscarPorId(atendente.getId());
        return null;
    }

    public String confirmarServico() {
        servico = servicoDao.buscarPorId(servico.getId());
        return null;
    }

    public List<Servico> servicosAtendente() {
        return agendamentoHorarioDao.servicosAtendente(atendente.getId());
    }

    public List<Servico> todosOsServicos() {
        return servicoDao.todosOsServicos();
    }

    public List<Atendente> atendentesExecutoresDoServico() {
        return atendenteDao.atendentesExecutoresDoServico(servico.getId());
    }

    public String agendarHorario() {

        Cliente cliente = (Cliente) sessao.getAttribute("cliente");

        //Seta dependencias no agendamento
        agendamento.setServico(servico);
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        agendamento.setHorario(LocalTime.parse(horarioAgendamento, time));
        agendamento.setData(dataAgendamento);
        agendamento.setAtendente(atendente);
        agendamento.setCliente(cliente);

        agendamentoHorarioDao.agendarHorarioAtendimento(agendamento);

        agendamento = new Agendamento();

        return "agendamento-horario-porservico.xhtml?faces-redirect=true";

    }

    public List<LocalTime> listarHorariosDisponiveisAtendente() {
        List<LocalTime> horariosDisponiveisAtendente
                = agendamentoHorarioDao.getHorariosDisponiveisAtendente(
                        atendente.getId(), servico, dataAgendamento);
        if (horariosDisponiveisAtendente.isEmpty()) {
            String tituloPagina = "agendamento-horario-porservico.xhtml";
            String mensagem = "O atendente não possui expediente neste dia, "
                    + "escolha um outro dia!";
            mensagemErro(tituloPagina, mensagem);
        }

        return horariosDisponiveisAtendente;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String getHorarioAgendamento() {
        return horarioAgendamento;
    }

    public void setHorarioAgendamento(String horarioAgendamento) {
        this.horarioAgendamento = horarioAgendamento;
    }

    private void mensagemErro(String tituloPagina, String conteudo) {
        FacesMessage mensagemDeErro = new FacesMessage(conteudo);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(tituloPagina, mensagemDeErro);
    }

}
