/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.ConfirmacaoAtendimentoDao;
import br.edu.ifpb.wazbarber.interfaces.DaoPesquisa;
import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Pesquisa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author romulo
 */
@Named
@RequestScoped
public class ControladorDeConfirmAtendimento implements Serializable {

    @Inject
    private ConfirmacaoAtendimentoDao confirmAtendimentoDao;
    @Inject
    private DaoPesquisa pesquisaDao;
    
    private List<Agendamento> agendamentos;

    @PostConstruct
    public void init() {
        agendamentos = confirmAtendimentoDao.agendamentosParaConfirmacao();
    }

//    public List<Agendamento> getAtendimentosParaConfirmacao() {
//        return confirmAtendimentoDao.agendamentosParaConfirmacao();
//    }
    public String confirmarAtendimento(Agendamento agendamento) {
        agendamentos.remove(agendamento);
        confirmAtendimentoDao.confirmarAtendimento(agendamento.getId());
        Pesquisa pesquisa = new Pesquisa(false, agendamento);
        pesquisaDao.cadastrar(pesquisa);
        return null;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

}
