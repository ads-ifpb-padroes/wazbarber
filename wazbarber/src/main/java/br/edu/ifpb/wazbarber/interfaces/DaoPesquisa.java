
package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.Pesquisa;
import java.math.BigDecimal;
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
    Double getMediaTempoEspera();
    Double getMediaQualidadeAtendimento();
    Double getMediaAmbiente();
    Double getMediaQualidadeServico();
    Double getMediaTempoEspera(int atendente);
    Double getMediaQualidadeAtendimento(int atendente);
    Double getMediaAmbiente(int atendente);
    Double getMediaQualidadeServico(int atendente);
}
