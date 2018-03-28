package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.model.Atendente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jozimar
 */
@Stateless
public class AtendenteDao implements DaoAtendente {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    @Override
    public void cadastrar(Atendente atendente) {
        entityManager.persist(atendente);
    }

    @Override
    public Atendente buscarPorId(int id) {
        Atendente atendente = entityManager.find(Atendente.class, id);
        return atendente;
    }

    @Override
    public void atualizar(Atendente atendente) {
        entityManager.merge(atendente);
    }

    @Override
    public List<Atendente> todosOsAtendentes() {
        String sqlQuery = "SELECT atendente FROM Atendente atendente ";
        TypedQuery<Atendente> query = entityManager.createQuery(sqlQuery, Atendente.class);
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }
}
