/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.wazbarber.servicos;

import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Pesquisa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author romulo
 */
@Stateless
public class ServicoEnvioEmail {
    private final String EMAIL = "wazbarbershop@gmail.com";
        private final String SENHA = "wazbarber123";
    private final Email email = new SimpleEmail();

    @PostConstruct
    public void init() {
        email.setHostName("smtp.googlemail.com");
        email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
        email.setTLS(true);
        email.setSSL(true);
    }

    public void enviarEmail(Agendamento agendamento) {
        try {
            email.setFrom(EMAIL);
            email.addTo(agendamento.getCliente().getEmail());
            email.setMsg("Olá, você agendou um serviço na wazbarber, "
                    + "veja os dados. \n\nServiço: " + agendamento.getServico()
                    + "\nAtendente: " + agendamento.getAtendente()
                    + "\nData: " + agendamento.getData()
                    + "\nHorário: " + agendamento.getHorario());
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(ServicoEnvioEmail.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarEmail(Pesquisa pesquisa){
         try {
            email.setFrom(EMAIL);
            email.addTo(pesquisa.getEmail());
            email.setMsg("Olá, responda a pesquisa de satisfação da WAZBarber, "
                    + "acesse o link: http://localhost:8080/wazbarber/pesquisa.xhtml?id="+pesquisa.getId());
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(ServicoEnvioEmail.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
