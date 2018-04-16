
package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoPesquisa;
import br.edu.ifpb.wazbarber.model.Pesquisa;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorPesquisa {
    private Pesquisa pesquisa = new Pesquisa();
    private boolean respondeu = false;
    @Inject
    private DaoPesquisa pesquisaDao;
    
    public String responder(int id){
        Pesquisa busca = pesquisaDao.buscarPorId(id);
        if(busca.isStatus()){
            mensagemErro("Erro", "Essa pesquisa já foi respondida!");
        }else{
            busca.setAmbiente(pesquisa.getAmbiente());
            busca.setQualidadeAtendimento(pesquisa.getQualidadeAtendimento());
            busca.setQualidadeServico(pesquisa.getQualidadeServico());
            busca.setTempoEspera(pesquisa.getTempoEspera());
            busca.setStatus(true);
            pesquisaDao.atualizar(busca);
            respondeu = true;
        }
        return null;
    }
    
    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }


    public boolean isRespondeu() {
        return respondeu;
    }

    public void setRespondeu(boolean respondeu) {
        this.respondeu = respondeu;
    }

    private void mensagemErro(String tituloPagina, String conteudo) {
        FacesMessage mensagemDeErro = new FacesMessage(conteudo);
        mensagemDeErro.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(tituloPagina, mensagemDeErro);
    }
    
    
}
