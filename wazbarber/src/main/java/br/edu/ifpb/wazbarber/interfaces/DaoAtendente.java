package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.Atendente;
import java.util.List;

/**
 *
 * @author jozimar
 */
public interface DaoAtendente {

    public void cadastrar(Atendente atendente);

    public Atendente buscarPorId(int id);

    public List<Atendente> todosOsAtendentes();
    
    public void atualizar(Atendente novoEstado);
    
    public List<Atendente> atendentesExecutoresDoServico(int idServico);
    
}
