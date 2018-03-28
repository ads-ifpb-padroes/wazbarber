package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.DuracaoDoServico;
import br.edu.ifpb.wazbarber.model.Servico;
import java.util.List;

/**
 *
 * @author jozimar
 */
public interface DaoServico {

    public void cadastrar(Servico servico);

    public void cadastrarDuracao(DuracaoDoServico duracaoDoServico);

    public Servico buscarPorId(int id);

    public void atualizar(Servico servico);

    public List<Servico> todosOsServicos();
}
