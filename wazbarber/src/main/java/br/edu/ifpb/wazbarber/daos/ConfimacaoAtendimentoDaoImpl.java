/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.ConfirmacaoAtendimentoDao;
import br.edu.ifpb.wazbarber.model.Agendamento;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author romulo
 */
@Stateless
public class ConfimacaoAtendimentoDaoImpl implements ConfirmacaoAtendimentoDao {

    @PersistenceContext(unitName = "persistencia")
    private EntityManager entityManager;

    @Override
    public void confirmarAtendimento(int idAgendamento) {
        Agendamento agendamento = entityManager
                .find(Agendamento.class, idAgendamento);
        agendamento.setConfirmado(true);
        entityManager.merge(agendamento);
    }
    //Nao observe performace, a gente sabe fazer da maneira certa; o tempo eh o dono do destino

    @Override
    public List<Agendamento> agendamentosParaConfirmacao() {

        String querySql = "SELECT a FROM Agendamento a "
                + "WHERE a.confirmado = false "
                + "ORDER BY a.data";
        TypedQuery<Agendamento> query = entityManager
                .createQuery(querySql, Agendamento.class);

        List<Agendamento> agendamentos = query.getResultList();
        List<Agendamento> agendamentoResultado = new ArrayList<>();

        if (agendamentos == null) {
            return new ArrayList<>();
        } else {
            for (Agendamento agendamento : agendamentos) {

                if (LocalDate.now().isAfter(agendamento.getData())
                        || (LocalDate.now().equals(agendamento.getData())
                        && LocalTime.now().isAfter(agendamento.getHorario()))) {

                    agendamentoResultado.add(agendamento);
                }
            }
            return agendamentoResultado;
        }
    }

//    @Override
//    public List<Aluno> listarTodosOsAlunos(int id) {
//
//        String querySql = "SELECT * FROM aluno a WHERE a.id <> " + id;
//
//        Query createNativeQuery = em
//                .createNativeQuery(querySql, Aluno.class);
//
//        List<Aluno> alunos = createNativeQuery.getResultList();
//
//        if (alunos == null) {
//            return new ArrayList<>();
//        }
//
//        return alunos;
//    }
}
