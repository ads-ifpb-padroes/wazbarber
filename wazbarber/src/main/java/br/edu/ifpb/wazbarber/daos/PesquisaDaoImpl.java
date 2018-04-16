
package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.DaoPesquisa;
import br.edu.ifpb.wazbarber.model.Pesquisa;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lyndemberg
 */
@Stateless
public class PesquisaDaoImpl implements DaoPesquisa{

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;
    
    @Override
    public void cadastrar(Pesquisa nova) {
        entityManager.persist(nova);
    }

    @Override
    public Pesquisa buscarPorIdAgendamento(int agendamento) {
        TypedQuery<Pesquisa> query = entityManager.createQuery("SELECT p FROM Pesquisa p "
                + "WHERE p.agendamento.id=:id",Pesquisa.class);
        query.setParameter("id", agendamento);
        Optional<Pesquisa> first = query.getResultList().stream().findFirst();
        if(first.isPresent()){
            return first.get();
        }else{
            return null;
        }
    }

    @Override
    public List<Pesquisa> getPesquisasParaEnvio() {
        LocalDate hoje = LocalDate.now();
        LocalDate menosDoisDias = hoje.minusDays(2);
        TypedQuery<Pesquisa> query = entityManager.createQuery("SELECT p FROM Pesquisa p WHERE p.status=false "
                + " AND p.agendamento.data=:data", Pesquisa.class);
        query.setParameter("data", menosDoisDias);
        return query.getResultList();
    }

    @Override
    public Pesquisa buscarPorId(int id) {
        return entityManager.find(Pesquisa.class, id);
    }

    @Override
    public void atualizar(Pesquisa atualizada) {
        entityManager.merge(atualizada);
    }
    
}
