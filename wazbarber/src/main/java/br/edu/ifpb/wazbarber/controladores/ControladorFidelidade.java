package br.edu.ifpb.wazbarber.controladores;

import br.edu.ifpb.wazbarber.interfaces.DaoCliente;
import br.edu.ifpb.wazbarber.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author lyndemberg
 */
@Named
@RequestScoped
public class ControladorFidelidade {
    @Inject
    private DaoCliente clienteDao;
    
    private String categoria;
    private int filtroMeses;
    private List<Cliente> fieis;
    
    public String buscar(){
        if(categoria.equals("Frequentes")){
            fieis = clienteDao.clientesFieisFrequentes(filtroMeses);
        }else if(categoria.equals("Gastantes")){
            fieis = clienteDao.clientesFieisPagantes(filtroMeses);
        }
        return null;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getFiltroMeses() {
        return filtroMeses;
    }

    public void setFiltroMeses(int filtroMeses) {
        this.filtroMeses = filtroMeses;
    }

    public List<Cliente> getFieis() {
        return fieis;
    }

    public void setFieis(List<Cliente> fieis) {
        this.fieis = fieis;
    }
    
    
    
    
    
}
