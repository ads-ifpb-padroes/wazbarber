package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.DaoAtendente;
import br.edu.ifpb.wazbarber.model.Atendente;
import br.edu.ifpb.wazbarber.model.Servico;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public void atualizar(Atendente novoEstado) {
        entityManager.merge(novoEstado);
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

    @Override
    public List<Atendente> atendentesExecutoresDoServico(int idServico) {

        String querySql = "SELECT a FROM Atendente a";
        
        TypedQuery<Atendente> query = entityManager
                .createQuery(querySql, Atendente.class);
        
        List<Atendente> atendestesExecutores = new ArrayList<>();
        
        if(query.getResultList()  == null){
            return new ArrayList<>();
        }else{
            for (Atendente atendente : query.getResultList()){
                for(Servico servico : atendente.getServicos()){
                    if(servico.getId() == idServico){
                        atendestesExecutores.add(atendente);
                    }
                }
            }
        
        }
        return atendestesExecutores;
    }

}
