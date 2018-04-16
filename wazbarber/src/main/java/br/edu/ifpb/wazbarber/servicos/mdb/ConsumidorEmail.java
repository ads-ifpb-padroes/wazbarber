/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.servicos.mdb;

import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Pesquisa;
import br.edu.ifpb.wazbarber.servicos.ServicoEnvioEmail;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author romulo
 */
@MessageDriven(
        mappedName = "java:global/jms/queueEmail",
        activationConfig = {
            @ActivationConfigProperty(propertyName 
                    = "destinationType", propertyValue = "javax.jms.Queue"),
            @ActivationConfigProperty(propertyName 
                    = "destinationName", propertyValue = "queueEmail")}
)

public class ConsumidorEmail implements MessageListener {

    @Inject
    private ServicoEnvioEmail sender;

    @Override
    public void onMessage(Message message) {
        try {
            String categoria = message.getStringProperty("categoria");
            if(categoria.equals("notificacao")){
                Agendamento agendamento = message.getBody(Agendamento.class);
                sender.enviarEmail(agendamento);
            }else if(categoria.equals("pesquisa")){
                Pesquisa pesquisa = message.getBody(Pesquisa.class);
                sender.enviarEmail(pesquisa);
            }

        } catch (JMSException ex) {
            Logger.getLogger(ConsumidorEmail.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
