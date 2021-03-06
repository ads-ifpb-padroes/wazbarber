/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.servicos;

import br.edu.ifpb.wazbarber.interfaces.AgendamentoHorarioDao;
import br.edu.ifpb.wazbarber.interfaces.DaoPesquisa;
import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Pesquisa;
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
    @Inject
    private DaoPesquisa pesquisaDao;
    
    
//    @Schedule(dayOfMonth = "*", hour="*", minute="*", second="*/20")
    @Schedule(dayOfMonth = "*", hour="*/12")
    public void agendadorEnviarEmail() {
        System.out.println("j");
        List<Agendamento> agendamentos = agendamentoHorarioDao
                .buscaAgendamentosComMinimoUmDiaAntecedente();

        for (Agendamento agendamento : agendamentos) {
            produtorEmail.enviar(agendamento);
        }
        
        List<Pesquisa> pesquisasParaEnvio = pesquisaDao.getPesquisasParaEnvio();
        System.out.println(pesquisasParaEnvio.toString());
        for(Pesquisa pesquisa : pesquisasParaEnvio){
            produtorEmail.enviar(pesquisa);
        }
        
    }

}
