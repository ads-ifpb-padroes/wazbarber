package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.builder.ClienteBuilder;
import br.edu.ifpb.wazbarber.builder.ClienteBuilderException;
import br.edu.ifpb.wazbarber.interfaces.DaoCliente;
import br.edu.ifpb.wazbarber.model.Agendamento;
import br.edu.ifpb.wazbarber.model.Cliente;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jozimar
 */
@Stateless
public class ClienteDao implements DaoCliente {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    @Override
    public void cadastrar(Cliente cliente) {
        entityManager.persist(cliente);
    }

    @Override
    public void atualizar(int idCliente, List<Agendamento> agendamentos) {
        Cliente cliente = entityManager.find(Cliente.class, idCliente);

        ClienteBuilder clienteBuilder = new ClienteBuilder();
        clienteBuilder.comAgendamentos(agendamentos)
                .comApelido(cliente.getApelido())
                .comCelular(cliente.getCelular())
                .comCidade(cliente.getCidade())
                .comEmail(cliente.getEmail())
                .comId(cliente.getId())
                .comNomeCompleto(cliente.getNomeCompleto())
                .comSenha(cliente.getSenha());

        try {
            Cliente clienteMerge = clienteBuilder.toCliente();
            entityManager.merge(clienteMerge);
        } catch (ClienteBuilderException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Cliente consultarPorEmail(String email) {
        TypedQuery<Cliente> query = entityManager
                .createQuery("SELECT cliente FROM Cliente cliente "
                        + "WHERE cliente.email=:email", Cliente.class);
        query.setParameter("email", email);
        Optional<Cliente> cliente = query.getResultList().stream().findFirst();
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }

    @Override
    public Cliente autenticarCliente(String email, String senha) {
        TypedQuery<Cliente> query = entityManager
                .createQuery("SELECT c FROM Cliente c WHERE c.email=:email "
                        + "AND c.senha=:senha", Cliente.class);
        query.setParameter("email", email);
        query.setParameter("senha", senha);
        Optional<Cliente> cliente = query.getResultList().stream().findFirst();
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }

    }
}
