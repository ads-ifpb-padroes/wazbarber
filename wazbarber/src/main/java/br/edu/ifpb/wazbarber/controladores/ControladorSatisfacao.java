
package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.interfaces.DaoPesquisa;
import br.edu.ifpb.wazbarber.model.Atendente;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author lyndemberg
 */
@Named
@SessionScoped
public class ControladorSatisfacao implements Serializable{
    
    @Inject
    private DaoPesquisa pesquisaDao;
    @Inject
    private DaoAtendente atendenteDao;
    
    
    private List<Atendente> atendentes;
    private String filtro;
    private int atendenteId;
    private boolean verResultados = false;
    private Double tempoEspera;
    private Double qualidadeAtendimento;
    private Double ambiente;
    private Double qualidadeServico;

    
    public String filtrar(){
        verResultados = false;
        if(filtro.equals("Geral")){
            tempoEspera = pesquisaDao.getMediaTempoEspera();
            qualidadeAtendimento = pesquisaDao.getMediaQualidadeAtendimento();
            ambiente = pesquisaDao.getMediaAmbiente();
            qualidadeServico = pesquisaDao.getMediaQualidadeServico();
            verResultados = true;
        }else{
            atendentes = atendenteDao.todosOsAtendentes();
        }
        return null;
    }
    
    public String mostrarValoresAtendente(){
        tempoEspera = pesquisaDao.getMediaTempoEspera(atendenteId);
        qualidadeAtendimento = pesquisaDao.getMediaQualidadeAtendimento(atendenteId);
        ambiente = pesquisaDao.getMediaAmbiente(atendenteId);
        qualidadeServico = pesquisaDao.getMediaQualidadeServico(atendenteId);
        verResultados = true;
        return null;
    }
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Double getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(Double tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public Double getQualidadeAtendimento() {
        return qualidadeAtendimento;
    }

    public void setQualidadeAtendimento(Double qualidadeAtendimento) {
        this.qualidadeAtendimento = qualidadeAtendimento;
    }

    public Double getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Double ambiente) {
        this.ambiente = ambiente;
    }

    public Double getQualidadeServico() {
        return qualidadeServico;
    }

    public void setQualidadeServico(Double qualidadeServico) {
        this.qualidadeServico = qualidadeServico;
    }

    public List<Atendente> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<Atendente> atendentes) {
        this.atendentes = atendentes;
    }

    public int getAtendenteId() {
        return atendenteId;
    }

    public void setAtendenteId(int atendenteId) {
        this.atendenteId = atendenteId;
    }

    public boolean isVerResultados() {
        return verResultados;
    }

    public void setVerResultados(boolean verResultados) {
        this.verResultados = verResultados;
    }
    
    
    
    
    
    
}
