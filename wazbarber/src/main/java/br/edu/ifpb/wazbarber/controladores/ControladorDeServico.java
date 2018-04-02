package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoServico;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.Servico;
import java.io.Serializable;
import java.util.List;
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
public class ControladorDeServico implements Serializable {

    private Servico servico;

    private Atendente atendente;

    @Inject
    private DaoServico daoServico;

    @PostConstruct
    public void instanceObjects() {
        this.servico = new Servico();
    }

    public String cadastrar() {
        daoServico.cadastrar(servico);
        return "areaadmin.xhtml?faces-redirect=true";
    }

    public List<Servico> todosOsServicos() {
        return daoServico.todosOsServicos();
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
}
