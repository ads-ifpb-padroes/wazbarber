/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.AgendamentoHorarioDao;
import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.HorarioAtendimento;
import br.edu.ifpb.wazbarber.model.Servico;
import br.edu.ifpb.wazbarber.model.enums.Dia;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author romulo
 */
@Stateless
public class AgendamentoHorarioDaoImpl implements AgendamentoHorarioDao {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    @Override
    public void agendarHorarioAtendimento(Agendamento agendamento) {
        entityManager.persist(agendamento);
    }

    @Override
    public boolean isPassivoDeAgendamento(
            int idAtendente, LocalDate dataAgendamento,
            LocalTime horaProxAgendamento, int duracaoServico) {

        Dia dia = getNameDia(dataAgendamento);

        List<HorarioAtendimento> horariosAtendimento
                = horariosAtendimentoAtendente(idAtendente, dia);

        List<Agendamento> agendamentosAtendente
                = agendamentosAtendente(idAtendente, dataAgendamento);

        for (HorarioAtendimento horarioAtendimento : horariosAtendimento) {

            LocalTime inicioExpediente = horarioAtendimento.getHoraChegada();
            LocalTime fimExpediente = horarioAtendimento.getHoraSaida();

            LocalTime fimProxAgendamento = horaProxAgendamento
                    .plus(duracaoServico, ChronoUnit.MINUTES);

            long duracaoExpediente = ChronoUnit.MINUTES
                    .between(inicioExpediente, fimExpediente);

            long duracaoAgendamento = ChronoUnit.MINUTES
                    .between(horaProxAgendamento, fimProxAgendamento);

            if (duracaoExpediente < duracaoAgendamento) {
                return false;
            }

            if (fimProxAgendamento.compareTo(fimExpediente) == 1) {
                return false;
            }

            if (horaProxAgendamento.compareTo(fimExpediente) == 0) {
                return false;
            }

            if (agendamentosAtendente == null) {
                return true;
            }

            for (Agendamento agendamento : agendamentosAtendente) {

                LocalTime inicioAgendamento = agendamento.getHorario();

                int duracaoServicoAgendamento = agendamento.getServico().getDuracao();

                LocalTime fimAgendamento = inicioAgendamento
                        .plus(duracaoServicoAgendamento, ChronoUnit.MINUTES);

                if (horaProxAgendamento.compareTo(inicioAgendamento) != -1
                        && horaProxAgendamento.compareTo(fimAgendamento) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    //##tratar quando não haver resultado banco
    private List<HorarioAtendimento> horariosAtendimentoAtendente(int idAtendente, Dia dia) {

        //Debug Expresso
        System.out.println("DIA CONVERTIDO: " + dia);

        String querySql = "SELECT a FROM Atendente a "
                + "WHERE a.id= :id";
        TypedQuery<Atendente> query = entityManager
                .createQuery(querySql, Atendente.class);
        query.setParameter("id", idAtendente);
        Optional<Atendente> atendente = query.getResultList().stream().findFirst();
        if (atendente.isPresent()) {
//            return atendente.get().getHorariosAtendimento().stream()
//                    .filter((HorarioAtendimento h) -> h.getDia() == dia)
//                            .collect(Collectors.toList());
            List<HorarioAtendimento> horariosAtendimento = new ArrayList<>();
            for (HorarioAtendimento horarioAtendimento : atendente.get().getHorariosAtendimento()) {
                if (horarioAtendimento.getDia() == dia) {
                    horariosAtendimento.add(horarioAtendimento);
                }
            }
            return horariosAtendimento;
        } else {
            return null;
        }
    }

    private List<Agendamento> agendamentosAtendente(int idAtendente, LocalDate data) {
        String querySql = "SELECT a FROM Agendamento a "
                + "WHERE a.atendente.id= :id AND a.data= :data";
        TypedQuery<Agendamento> query = entityManager
                .createQuery(querySql, Agendamento.class);
        query.setParameter("id", idAtendente);
        query.setParameter("data", data);

        return query.getResultList();
    }

    private Dia getNameDia(LocalDate data) {
        int diaParaEnum = data.getDayOfWeek().getValue();
        switch (diaParaEnum) {
            case 7:
                return Dia.DOMINGO;
            case 1:
                return Dia.SEGUNDA;
            case 2:
                return Dia.TERCA;
            case 3:
                return Dia.QUARTA;
            case 4:
                return Dia.QUINTA;
            case 5:
                return Dia.SEXTA;
            default:
                return Dia.SABADO;
        }
    }

    @Override
    public List<Servico> servicosAtendente(int idAtendente) {
        String querySql = "SELECT a FROM Atendente a WHERE a.id= :id";
        TypedQuery<Atendente> query = entityManager
                .createQuery(querySql, Atendente.class);
        query.setParameter("id", idAtendente);
        Optional<Atendente> atendente = query.getResultList().stream().findFirst();
        if (atendente.isPresent()) {
            return atendente.get().getServicos();
        } else {
            return null;
        }
    }

}
