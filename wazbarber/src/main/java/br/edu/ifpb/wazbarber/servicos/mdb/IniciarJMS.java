/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.servicos.mdb;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;

/**
 *
 * @author romulo
 */
@JMSDestinationDefinitions(value = {
    @JMSDestinationDefinition(
        name = "java:global/jms/queueEmail",
        interfaceName = "javax.jms.Queue",
        resourceAdapter = "jmsra",
        destinationName = "queueEmail")
})
@Singleton
@Startup
public class IniciarJMS {
    public IniciarJMS(){
        System.out.println("Criando todos os destinos JMS");
    }
}
