package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.AgendamentoHorarioDao;
import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.interfaces.DaoCliente;
import br.edu.ifpb.wazbarber.interfaces.DaoServico;
import br.edu.ifpb.wazbarber.model.Administrador;
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
public class ControladorDeAgendamento implements Serializable {

    private Agendamento agendamento;
    private String horarioAgendamento;
    private LocalDate dataAgendamento;
    private Atendente atendente;
    private int idClienteDoSelect;
    private Cliente clienteDoSelect;
    private Servico servico;
    private HttpSession sessao;

    @Inject
    private AgendamentoHorarioDao agendamentoHorarioDao;
    @Inject
    private DaoServico servicoDao;
    @Inject
    private DaoAtendente atendenteDao;
    @Inject
    private DaoCliente clienteDao;

    private Cliente clienteDaSessao;
    private Administrador administradorDaSessao;
    private boolean ehSessaoCliente;

    @PostConstruct
    public void instanceObjects() {
        this.agendamento = new Agendamento();
        this.atendente = new Atendente();
        this.servico = new Servico();
        this.sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        clienteDaSessao = (Cliente) sessao.getAttribute("cliente");
        administradorDaSessao = (Administrador) sessao.getAttribute("admin");

        setSessaoCliente();

    }

    public void setSessaoCliente() {
        if (clienteDaSessao == null) {
            setEhSessaoCliente(false);
        } else {
            setEhSessaoCliente(true);
        }
    }

    public String confirmarAtendente() {
        atendente = atendenteDao.buscarPorId(atendente.getId());
        return null;
    }

//    public String confirmarClienteDoSelect(){
//        clienteDoSelect = clienteDao.buscarPorId(idClienteDoSelect);
//        return null;
//    }
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

    public List<Cliente> listarTodosOsClientes() {
        return clienteDao.getTodosOsClientes();
    }

    public List<Atendente> atendentesExecutoresDoServico() {
        return atendenteDao.atendentesExecutoresDoServico(servico.getId());
    }

    public String agendarHorario() {

        //Seta dependencias no agendamento
        agendamento.setServico(servico);
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        agendamento.setHorario(LocalTime.parse(horarioAgendamento, time));
        agendamento.setData(dataAgendamento);
        agendamento.setAtendente(atendente);

        if (ehSessaoCliente) {
            agendamento.setCliente(clienteDaSessao);
        } else {
            clienteDoSelect = clienteDao.buscarPorId(idClienteDoSelect);
            agendamento.setCliente(clienteDoSelect);
        }

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
            String mensagem = "O atendente não possui expediente neste dia "
                    + "ou seus horarios foram esgoatados, escolha um outro dia!";
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

    public boolean isEhSessaoCliente() {
        return ehSessaoCliente;
    }

    public void setEhSessaoCliente(boolean ehSessaoCliente) {
        this.ehSessaoCliente = ehSessaoCliente;
    }

    public Cliente getClienteDoSelect() {
        return clienteDoSelect;
    }

    public int getIdClienteDoSelect() {
        return idClienteDoSelect;
    }

    public void setIdClienteDoSelect(int idClienteDoSelect) {
        this.idClienteDoSelect = idClienteDoSelect;
    }

    private void mensagemErro(String tituloPagina, String conteudo) {
        FacesMessage mensagemDeErro = new FacesMessage(conteudo);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(tituloPagina, mensagemDeErro);
    }

    ////Vai me servir em DAC
////    public String confirmarAgendamento() throws ClienteBuilderException {
////        
////        int idAtendente = atendente.getId();
////        int duracaoServico = servico.getDuracao();
////        
////        if (!agendamentoHorarioDao.isPassivoDeAgendamento(
////                idAtendente, dataAgendamento, horario, duracaoServico)) {
////            String tituloPagina = "agendamento-horario.xhtml";
////            String mensagemErro = "Impossível o agendamento, "
////                    + "escolha outro horario ou data";
////            mensagemErro(tituloPagina, mensagemErro);
////            return "agendamento-horario.xhtml";
////        } else {
////            agendamento.setHorario(horario);
////            agendamento.setData(dataAgendamento);
////            agendamento.setAtendente(atendente);
////
////            //Pega Cliente da sessão
////            Cliente cliente = (Cliente) sessao.getAttribute("cliente");
////
////            //Adiciona agendamento na lista auxiliar para o builder
////            agendamentos.add(agendamento);
////
////            //Monta o cliente através do builder
////            clienteBuilder.comAgendamentos(agendamentos).comId(cliente.getId())
////                    .comEmail(cliente.getEmail()).comSenha(cliente.getSenha());
////            clienteDao.atualizar(clienteBuilder.toCliente());
////
////            //Adiciona o agendamento e atualiza
////            atendente.addAgendamentos(agendamento);
////            daoAtendente.atualizar(atendente);
////            
////            agendamento.setCliente(cliente);
////            agendamento.setServico(servico);
////            agendamentoHorarioDao.agendarHorarioAtendimento(agendamento);
////        }
////        agendamento = new Agendamento();
////        return "inicial.xhtml?faces-redirect=true";
////    }
//    //Metodo de Teste Funcional
////    public List<LocalTime> pegaHorariosLivres(){
////        
////        Servico servicot = daoServico.buscarPorId(this.servico.getId());
////        
////        return new AgendamentoHorarioUtil().horariosDisponiveisAtendente(
////                atendente.getId(), servicot.getDuracao(), dataAgendamento);
////    }
//}
}
