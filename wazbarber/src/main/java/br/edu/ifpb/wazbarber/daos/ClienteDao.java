package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.DaoCliente;
import br.edu.ifpb.wazbarber.model.Cliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void atualizar(Cliente cliente) {
        entityManager.merge(cliente);
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
    public Cliente buscarPorId(int idCliente) {
        return entityManager.find(Cliente.class, idCliente);
    }

    @Override
    public List<Cliente> getTodosOsClientes() {

        String querySql = "SELECT c FROM Cliente c";

        TypedQuery<Cliente> query = entityManager
                .createQuery(querySql, Cliente.class);

        if (query.getResultList() == null) {
            return new ArrayList<>();
        } else {
            return query.getResultList();
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

    @Override
    public List<Cliente> clientesFieisFrequentes(int filtroMeses) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = hoje.minusMonths(filtroMeses);
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT a.cliente FROM Agendamento a WHERE "
                + "(a.data=:inicio OR a.data>:inicio) AND (a.data=:hoje OR a.data<:hoje) "
                + " AND a.confirmado=true "
                + "GROUP BY a.cliente ORDER BY COUNT(a) DESC", Cliente.class);
        query.setParameter("inicio", inicio);
        query.setParameter("hoje", hoje);
        return query.getResultList();
    }

    @Override
    public List<Cliente> clientesFieisPagantes(int filtroMeses) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicio = hoje.minusMonths(filtroMeses);
        TypedQuery<Cliente> query = entityManager.createQuery("SELECT a.cliente FROM Agendamento a WHERE "
                + "(a.data=:inicio OR a.data>:inicio) AND (a.data=:hoje OR a.data<:hoje) "
                + " AND a.confirmado=true "
                + "GROUP BY a.cliente ORDER BY SUM(a.servico.preco) DESC", Cliente.class);
        query.setParameter("inicio", inicio);
        query.setParameter("hoje", hoje);
        return query.getResultList();
    }
    
    
}
