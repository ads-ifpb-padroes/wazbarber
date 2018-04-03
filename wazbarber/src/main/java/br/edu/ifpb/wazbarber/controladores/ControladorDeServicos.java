package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.interfaces.DaoServico;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.DuracaoDoServicoAtendente;
import br.edu.ifpb.wazbarber.model.Servico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorDeServicos implements Serializable {

    private Atendente atendente;

    private Servico servico;

    private DuracaoDoServicoAtendente duracaoDoServico;

    @Inject
    private DaoAtendente daoAtendente;

    @Inject
    private DaoServico daoServico;

    @PostConstruct
    public void instanceObjects() {
        this.atendente = new Atendente();
        this.servico = new Servico();
        this.duracaoDoServico = new DuracaoDoServicoAtendente();
    }

    public String cadastrar() {
        Atendente atendenteBuscado = daoAtendente.buscarPorId(atendente.getId());
        Servico servicoBuscado = daoServico.buscarPorId(servico.getId());
        atendenteBuscado.addServicos(servico);
        daoAtendente.atualizar(atendenteBuscado);
        
        duracaoDoServico.setAtendente(atendenteBuscado);
        duracaoDoServico.setServico(servicoBuscado);

        daoServico.cadastrarDuracao(duracaoDoServico);
        return "areaadmin.xhtml?faces-redirect=true";
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

    public DuracaoDoServicoAtendente getDuracaoDoServico() {
        return duracaoDoServico;
    }

    public void setDuracaoDoServico(DuracaoDoServicoAtendente duracaoDoServico) {
        this.duracaoDoServico = duracaoDoServico;
    }
}
