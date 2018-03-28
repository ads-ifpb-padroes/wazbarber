package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoAdmin;
import br.edu.ifpb.wazbarber.model.Administrador;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jozimar
 */
@Named
@RequestScoped
public class ControladorDeAdmin implements Serializable {

    private Administrador administrador;
    private HttpSession sessao;

    @Inject
    private DaoAdmin daoAdmin;

    @PostConstruct
    public void instanceObjects() {
        this.administrador = new Administrador();
    }

    public String realizarlogin() {
        Administrador administradorLogado = daoAdmin.consultarPorLogin(administrador.getLogin());
        if (administradorLogado == null) {
            mensagemErro("Login Admin", "O administrador informado não está cadastrado!");
            return null;
        } else {
            Administrador administradorAutenticavel = daoAdmin
                    .autenticarAdmin(administrador.getLogin(), administrador.getSenha());
            if (administradorAutenticavel == null) {
                mensagemErro("Login Admin", "Os dados informados estão incorretos!");
                return null;
            } else {
                sessao = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);
                sessao.setAttribute("admin", administradorLogado);
                return "areaadmin.xhtml?faces-redirect=true";
            }
        }
    }

    public String realizarLogout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml?faces-redirect=true";
    }

    public void mensagemErro(String tituloPagina, String conteudo) {
        FacesMessage mensagemDeErro = new FacesMessage(conteudo);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(tituloPagina, mensagemDeErro);
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
}
