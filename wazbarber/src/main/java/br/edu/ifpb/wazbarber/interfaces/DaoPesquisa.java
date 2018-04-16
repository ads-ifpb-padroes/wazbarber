
package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.Pesquisa;
import java.util.List;

/**
 *
 * @author lyndemberg
 */
public interface DaoPesquisa {
    void cadastrar(Pesquisa nova);
    void atualizar(Pesquisa atualizada);
    Pesquisa buscarPorId(int id);
    Pesquisa buscarPorIdAgendamento(int agendamento);
    List<Pesquisa> getPesquisasParaEnvio();
}
