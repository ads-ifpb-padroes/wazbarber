/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
