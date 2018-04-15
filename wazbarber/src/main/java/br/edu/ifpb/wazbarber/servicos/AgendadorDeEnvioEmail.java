/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.servicos;

import br.edu.ifpb.wazbarber.interfaces.AgendamentoHorarioDao;
import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.servicos.mdb.ProdutorEmail;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author romulo
 */
@Singleton
@Startup
public class AgendadorDeEnvioEmail {

    @Inject
    private AgendamentoHorarioDao agendamentoHorarioDao;
    @Inject
    private ProdutorEmail produtorEmail;

    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void agendadorEnviarEmail() {

        List<Agendamento> agendamentos = agendamentoHorarioDao
                .buscaAgendamentosComMinimoUmDiaAntecedente();

        for (Agendamento agendamento : agendamentos) {
            produtorEmail.enviar(agendamento);
        }
    }

}
