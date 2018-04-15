/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Servico;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author romulo
 */
public interface AgendamentoHorarioDao {

    public void agendarHorarioAtendimento(Agendamento agendamento);

    public List<LocalTime> getHorariosDisponiveisAtendente(
            int idAtendente, Servico servico, LocalDate data);

    public List<Servico> servicosAtendente(int idAtendente);
    
    public List<Agendamento> buscaAgendamentosComMinimoUmDiaAntecedente();

}
