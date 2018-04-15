package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.Agendamento;
import java.util.List;

/**
 *
 * @author romulo
 */
public interface ConfirmacaoAtendimentoDao {

    public List<Agendamento> agendamentosParaConfirmacao();

    public void confirmarAtendimento(int idAgendamento);

}
