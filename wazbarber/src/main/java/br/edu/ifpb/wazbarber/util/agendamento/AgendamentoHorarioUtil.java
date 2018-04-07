/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.util.agendamento;

/**
 *
 * @author romulo
 */

//------Função deve ser salva no git, ainda incompleta e precisando substituir 
//------variaveis auxiliares corporais por parametros funcionais, mas com fundamentos

//public class AgendamentoHorarioUtil {

//    @Inject
//    private AgendamentoHorarioDao agendamentoHorarioDao;

//    public List<LocalTime> horariosDisponiveisAtendente(
//            int idAtendente, int duracaoServico) {
//
//        //Guarda os horários disponíveis para uma seleção
//        List<LocalTime> horariosDisponiveis = new ArrayList<>();
//        List<LocalTime> aux = new ArrayList<>();
//
//        List<HorarioAtendimento> horariosAtendimentos = agendamentoHorarioDao
//                .horariosAtendimentoAtendente(idAtendente, Dia.DOMINGO);
//
//        List<Agendamento> agendamentos = agendamentoHorarioDao
//                .agendamentosAtendente(idAtendente, LocalDate.now());
//
//        for (HorarioAtendimento horarioAtendimento : horariosAtendimentos) {
//
//            LocalTime inicioExpediente = horarioAtendimento.getHoraChegada();
//            LocalTime fimExpediente = horarioAtendimento.getHoraSaida();
//
//            for (Agendamento agendamento : agendamentos) {
//                LocalTime inicioAgendamento = agendamento.getHorario();
//                int duracaoServicoAgendado = agendamento.getServico().getDuracao();
//                LocalTime fimAgendamento = inicioAgendamento
//                        .plus(duracaoServicoAgendado, ChronoUnit.MINUTES);
//
//                LocalTime regressaoIntervalar = inicioAgendamento
//                        .plus(-30, ChronoUnit.MINUTES);
//                LocalTime progressaoIntervalar = fimAgendamento;
//
//                while (regressaoIntervalar.compareTo(inicioExpediente) != -1) {
//                    horariosDisponiveis.add(regressaoIntervalar);
//                    regressaoIntervalar = regressaoIntervalar
//                            .plus(-30, ChronoUnit.MINUTES);
//                }
//
//                while (fimExpediente.compareTo(progressaoIntervalar) == 1) {
//                    horariosDisponiveis.add(progressaoIntervalar);
//                    progressaoIntervalar = progressaoIntervalar
//                            .plus(30, ChronoUnit.MINUTES);
//                }
//
//                for (LocalTime horarioDisponivel : horariosDisponiveis) {
//                    if (horarioDisponivel.compareTo(fimAgendamento) == -1
//                            && horarioDisponivel
//                                    .compareTo(inicioAgendamento) != -1) {
//                        aux.add(horarioDisponivel);
//                    }
//                }
//            }
//        }
//        return aux;
//    }

//}
