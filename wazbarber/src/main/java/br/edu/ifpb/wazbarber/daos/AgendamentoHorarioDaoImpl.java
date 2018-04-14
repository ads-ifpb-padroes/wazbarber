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
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
    public List<LocalTime> getHorariosDisponiveisAtendente(
            int idAtendente, Servico servico, LocalDate data) {

        Dia diaAgendamento = getEnumDia(data);
        System.out.println("######DIA###### " + diaAgendamento);

        HorarioAtendimento horarioAtendimento
                = horarioAtendimentoAtendente(idAtendente, diaAgendamento);

        if (horarioAtendimento != null) {

            System.out.println("####HORARIO DE ATENDIMENTO#### " + horarioAtendimento);

            List<Agendamento> agendamentos
                    = agendamentosAtendente(idAtendente, data);

            List<LocalTime> horariosDisponiveis = new LinkedList<>();

            /*
                O ponteiro começa no início do expediente, para que 
                possamos percorrer todos os horários disponíveis
             */
            LocalTime ponteiroHorario = horarioAtendimento.getHoraChegada();

            for (Agendamento agendamento : agendamentos) {

                int duracaoDesteAgendamento = agendamento.getServico().getDuracao();
                LocalTime horaInicioDesteAgendamento = agendamento.getHorario();
                LocalTime horaFimDesteAgendamento = horaInicioDesteAgendamento
                        .plus(duracaoDesteAgendamento, ChronoUnit.MINUTES);

                // Tempo disponível entre o ponteiro o horário do primeiro atendimento
                long tempoDisponivel = ChronoUnit.MINUTES.between(ponteiroHorario, horaInicioDesteAgendamento);
                // Quantos serviços deste tipo eu consigo "encaixar" neste tempo disponível
                int qtdeServicos = (int) (tempoDisponivel / servico.getDuracao());

                /*
                Caso não seja possível agendar um serviço neste espaço de tempo,
                defina o ponteiro para apontar para o fim deste atendimento
                 */
                if (qtdeServicos == 0) {
                    ponteiroHorario = horaFimDesteAgendamento;

                    // Vá para o próximo atendimento
                    continue;
                }

                /*
                Caso seja possível, faça a quantidade de atendimentos necessários
                e guarde na lista. O ponteiro sempre avança X minutos para criar o próximo,
                sendo X a quantidade de minutos definidos para um serviço (30 minutos, 60 minutos...)
                 */
                for (int i = 0; i < qtdeServicos; i++) {
                    horariosDisponiveis.add(ponteiroHorario);
                    ponteiroHorario = ponteiroHorario.plus(servico.getDuracao(), ChronoUnit.MINUTES);
                }

                /*
                Ao final, com este espaço "preenchido", pule para o horário final do atendimento que
                estamos comparando.
                 */
                ponteiroHorario = horaFimDesteAgendamento;

            }

            /*
            Caso todos os atendimentos já tenham sido comparados, ainda pode existir espaço
            para agendar outros atendimentos. Sendo assim, temos que verificar quantos
            serviços deste tipo conseguimos agendar com o tempo restante.
             */
            long tempoDisponivel1 = ChronoUnit.MINUTES.between(ponteiroHorario, horarioAtendimento.getHoraSaida());
            int qtdeServicos1 = (int) (tempoDisponivel1 / servico.getDuracao());
            for (int i = 0; i < qtdeServicos1; i++) {
                horariosDisponiveis.add(ponteiroHorario);
                ponteiroHorario = ponteiroHorario.plus(servico.getDuracao(), ChronoUnit.MINUTES);
            }

            // Ao final, temos todos os horários disponíveis
            return horariosDisponiveis;
        }
        return new ArrayList<>();
    }

    //Lista para o cliente escolher o servico
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
            return new ArrayList<>();
        }
    }

    private HorarioAtendimento horarioAtendimentoAtendente(
            int idAtendente, Dia dia) {

        String querySql = "SELECT a FROM Atendente a "
                + "WHERE a.id= :id";
        TypedQuery<Atendente> query = entityManager
                .createQuery(querySql, Atendente.class);
        query.setParameter("id", idAtendente);

        Optional<Atendente> atendente = query.getResultList().stream().findFirst();
        if (atendente.isPresent()) {
            List<HorarioAtendimento> horariosAtendimento
                    = atendente.get().getHorariosAtendimento();
            if (horariosAtendimento != null) {
                for (HorarioAtendimento horarioAtendimento : horariosAtendimento) {
                    if (horarioAtendimento.getDia() == dia) {
                        return horarioAtendimento;
                    }
                }
                return null;
            }
            return null;
        } else {
            return null;
        }
    }
    
    
    //Aqui falta refatorar a busca para trazer apenas os agendamentos não confirmados
    private List<Agendamento> agendamentosAtendente(int idAtendente, LocalDate data) {

        String querySql = "SELECT a FROM Agendamento a "
                + "WHERE a.atendente.id= :id AND a.data= :data "
                + "ORDER BY a.data, a.horario";
        TypedQuery<Agendamento> query = entityManager
                .createQuery(querySql, Agendamento.class);
        query.setParameter("id", idAtendente);
        query.setParameter("data", data);

        if (query.getResultList() == null) {
            return new ArrayList<>();
        }

        return query.getResultList();
    }

    private Dia getEnumDia(LocalDate data) {

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

}
