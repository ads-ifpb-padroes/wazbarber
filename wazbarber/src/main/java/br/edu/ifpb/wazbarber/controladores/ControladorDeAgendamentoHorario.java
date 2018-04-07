/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.builder.ClienteBuilder;
import br.edu.ifpb.wazbarber.builder.ClienteBuilderException;
import br.edu.ifpb.wazbarber.daos.ClienteDao;
import br.edu.ifpb.wazbarber.interfaces.AgendamentoHorarioDao;
import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.interfaces.DaoCliente;
import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.Cliente;
import br.edu.ifpb.wazbarber.model.Servico;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
public class ControladorDeAgendamentoHorario implements Serializable {
    
    private String opcaoAgendamento;
    private Agendamento agendamento;
    private LocalDate dataAgendamento;
    private LocalTime horario;
    private Atendente atendente;
    private Servico servico;
    private final ClienteBuilder clienteBuilder;
    private final List<Agendamento> agendamentos;
    private final HttpSession sessao;
    
    @Inject
    private AgendamentoHorarioDao agendamentoHorarioDao;
    @Inject
    private DaoCliente clienteDao;
    @Inject
    private DaoAtendente daoAtendente;
    
    public ControladorDeAgendamentoHorario() {
        this.agendamento = new Agendamento();
        this.atendente = new Atendente();
        this.servico = new Servico();
        this.clienteBuilder = new ClienteBuilder();
        this.agendamentos = new ArrayList<>();
        this.sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
    }

    //Método auxiliar temporário
    public String confirmar() {
        return null;
    }
    
    public String confirmarAtendente() {
        atendente = daoAtendente.buscarPorId(atendente.getId());
        agendamento.setAtendente(atendente);
        return null;
    }
    
    public String confirmarAgendamento() throws ClienteBuilderException {
        
        int idAtendente = atendente.getId();
        int duracaoServico = servico.getDuracao();
        
        if (!agendamentoHorarioDao.isPassivoDeAgendamento(
                idAtendente, dataAgendamento, horario, duracaoServico)) {
            String tituloPagina = "agendamento-horario.xhtml";
            String mensagemErro = "Impossível o agendamento, "
                    + "escolha outro horario ou data";
            mensagemErro(tituloPagina, mensagemErro);
            return "agendamento-horario.xhtml";
        } else {
            agendamento.setHorario(horario);
            agendamento.setData(dataAgendamento);
            agendamento.setAtendente(atendente);

            //Pega Cliente da sessão
            Cliente cliente = (Cliente) sessao.getAttribute("cliente");

            //Adiciona agendamento na lista auxiliar para o builder
            agendamentos.add(agendamento);

            //Monta o cliente através do builder
            clienteBuilder.comAgendamentos(agendamentos).comId(cliente.getId())
                    .comEmail(cliente.getEmail()).comSenha(cliente.getSenha());
            clienteDao.atualizar(clienteBuilder.toCliente());

            //Adiciona o agendamento e atualiza
            atendente.addAgendamentos(agendamento);
            daoAtendente.atualizar(atendente);
            
            agendamento.setCliente(cliente);
            agendamento.setServico(servico);
            agendamentoHorarioDao.agendarHorarioAtendimento(agendamento);
        }
        agendamento = new Agendamento();
        return "inicial.xhtml?faces-redirect=true";
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
    
    public List<Servico> servicosAtendente() {
        return agendamentoHorarioDao.servicosAtendente(atendente.getId());
    }
    
    public Servico getServico() {
        return servico;
    }
    
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public LocalTime getHorario() {
        return horario;
    }
    
    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
    
    private void mensagemErro(String tituloPagina, String conteudo) {
        FacesMessage mensagemDeErro = new FacesMessage(conteudo);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(tituloPagina, mensagemDeErro);
    }
}
