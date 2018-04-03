package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.enums.TipoDeAtendente;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorDeAtendente implements Serializable {

    private Atendente atendente;

    private Part foto;

    @Inject
    private DaoAtendente daoAtendente;

    @PostConstruct
    public void instanceObjects() {
        this.atendente = new Atendente();
    }

    public String cadastrar() throws IOException {

        byte[] arrayFoto = new byte[(int) foto.getSize()];
        foto.getInputStream().read(arrayFoto);
        atendente.setFoto(arrayFoto);

        daoAtendente.cadastrar(atendente);
        return "areaadmin.xhtml?faces-redirect=true";
    }

    public List<Atendente> todosOsAtendentes() {
        return daoAtendente.todosOsAtendentes();
    }

    public SelectItem[] getTiposAtendentes() {
        SelectItem[] items = new SelectItem[TipoDeAtendente.values().length];
        int i = 0;
        for (TipoDeAtendente t : TipoDeAtendente.values()) {
            items[i++] = new SelectItem(t, t.getLabel());
        }
        return items;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Part getFoto() {
        return foto;
    }

    public void setFoto(Part foto) {
        this.foto = foto;
    }
}
