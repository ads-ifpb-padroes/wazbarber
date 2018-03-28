package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.builder.ClienteBuilder;
import br.edu.ifpb.wazbarber.builder.ClienteBuilderException;
import br.edu.ifpb.wazbarber.interfaces.DaoCliente;
import br.edu.ifpb.wazbarber.model.Cliente;
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
public class ControladorDeCliente implements Serializable {

    ClienteBuilder clienteBuilder;
    private HttpSession sessao;

    private String nomeCompleto;
    private String apelido;
    private String celular;
    private String email;
    private String senha;
    private String cidade;

    Cliente cliente;

    @Inject
    private DaoCliente daoCliente;

    @PostConstruct
    public void instanceObjects() {
        this.clienteBuilder = new ClienteBuilder();
    }

    public String cadastrar() throws ClienteBuilderException {
        clienteBuilder.comNomeCompleto(nomeCompleto).comApelido(apelido).
                comCelular(celular).comEmail(email).comSenha(senha).
                comCidade(cidade).toCliente();
        cliente = clienteBuilder.toCliente();

        if (daoCliente.consultarPorEmail(cliente.getEmail()) != null) {
            mensagemErro("Cadastro de Clientes", "Já existe um cliente cadastrado "
                    + "com o e-mail informado!");
        } else {

            daoCliente.cadastrar(cliente);
            return "index.xhtml?faces-redirect=true";
        }
        return null;
    }

    public String realizarlogin() throws ClienteBuilderException {
        clienteBuilder.comEmail(email).comSenha(senha).toCliente();
        cliente = clienteBuilder.toCliente();
        Cliente clienteLogado = daoCliente.consultarPorEmail(cliente.getEmail());
        if (clienteLogado == null) {
            mensagemErro("Login", "O cliente informado não está cadastrado!");
            return null;
        } else {
            Cliente clienteAutenticavel = daoCliente
                    .autenticarCliente(cliente.getEmail(), cliente.getSenha());
            if (clienteAutenticavel == null) {
                mensagemErro("Login", "Os dados informados estão incorretos!");
                return null;
            } else {
                sessao = (HttpSession) FacesContext.getCurrentInstance()
                        .getExternalContext().getSession(true);
                sessao.setAttribute("cliente", clienteLogado);
                return "inicial.xhtml?faces-redirect=true";
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
