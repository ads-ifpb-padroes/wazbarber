package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.DaoServico;
import br.edu.ifpb.wazbarber.model.Servico;
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
public class ServicoDao implements DaoServico {
    
    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;
    
    @Override
    public void cadastrar(Servico servico) {
        entityManager.persist(servico);
    }
    
    @Override
    public Servico buscarPorId(int id) {
        Servico servico = entityManager.find(Servico.class, id);
        return servico;
    }
    
    @Override
    public void atualizar(Servico servico) {
        entityManager.merge(servico);
    }
    
    @Override
    public List<Servico> todosOsServicos() {
        String sqlQuery = "SELECT servico FROM Servico servico ";
        TypedQuery<Servico> query = entityManager.createQuery(sqlQuery, Servico.class);
        if (query.getResultList() == null) {
            return new ArrayList<>();
        }
        return query.getResultList();
    }
    
}
