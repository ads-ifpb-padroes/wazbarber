/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.model.enums.Dia;
import java.time.LocalDate;

/**
 *
 * @author romulo
 */
public class AgendamentoHorarioDao {
    
    public Dia getDiaDaSemana(LocalDate data){
        return Dia.valueOf(data.getDayOfWeek().name());
    }
    
}
