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

    public void atualizar(Atendente atendente);

    public List<Atendente> todosOsAtendentes();
}
