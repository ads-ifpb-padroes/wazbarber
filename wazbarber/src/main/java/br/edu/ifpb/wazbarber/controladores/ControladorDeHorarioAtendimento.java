package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.enums.Dia;
import br.edu.ifpb.wazbarber.model.HorarioAtendimento;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorDeHorarioAtendimento implements Serializable {

    private HorarioAtendimento horarioAtendimento;

    private Atendente atendente;

    @Inject
    private DaoAtendente daoAtendente;

    @PostConstruct
    public void instanceObjects() {
        this.horarioAtendimento = new HorarioAtendimento();
        this.atendente = new Atendente();
    }

    public String cadastrar() throws IOException {
        Atendente atendenteBuscado = daoAtendente.buscarPorId(atendente.getId());
        atendenteBuscado.addHorarioAtendimento(horarioAtendimento);

        daoAtendente.atualizar(atendenteBuscado);
        return "areaadmin.xhtml?faces-redirect=true";
    }

    public SelectItem[] getDias() {
        SelectItem[] items = new SelectItem[Dia.values().length];
        int i = 0;
        for (Dia d : Dia.values()) {
            items[i++] = new SelectItem(d, d.getLabel());
        }
        return items;
    }

    public HorarioAtendimento getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public void setHorarioAtendimento(HorarioAtendimento horarioAtendimento) {
        this.horarioAtendimento = horarioAtendimento;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
}
