/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.servicos.mdb;

import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Pesquisa;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 *
 * @author romulo
 */
@Stateless
public class ProdutorEmail {

    @Resource(lookup = "java:global/jms/queueEmail")
    private Queue fila;

    @Inject
    private JMSContext context;

    public void enviar(Agendamento agendamento) {
        JMSProducer createProducer = context.createProducer();
        createProducer.setProperty("categoria", "notificacao").send(fila, agendamento);
    }
    
    public void enviar(Pesquisa pesquisa) {
        JMSProducer createProducer = context.createProducer();
        createProducer.setProperty("categoria", "pesquisa").send(fila, pesquisa);
    }

}
