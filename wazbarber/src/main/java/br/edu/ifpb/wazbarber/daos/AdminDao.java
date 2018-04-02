package br.edu.ifpb.wazbarber.daos;

import br.edu.ifpb.wazbarber.interfaces.DaoAdmin;
import br.edu.ifpb.wazbarber.model.Administrador;
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
public class AdminDao implements DaoAdmin {

    @PersistenceContext(unitName = "persistencia")
    EntityManager entityManager;

    @Override
    public Administrador consultarPorLogin(String login) {
        TypedQuery<Administrador> query = entityManager
                .createQuery("SELECT administrador FROM Administrador administrador "
                        + "WHERE administrador.login=:login", Administrador.class);
        query.setParameter("login", login);
        Optional<Administrador> administrador = query.getResultList().stream().findFirst();
        if (administrador.isPresent()) {
            return administrador.get();
        } else {
            return null;
        }
    }

    @Override
    public Administrador autenticarAdmin(String login, String senha) {
        TypedQuery<Administrador> query = entityManager
                .createQuery("SELECT a FROM Administrador a WHERE a.login=:login "
                        + "AND a.senha=:senha", Administrador.class);
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        Optional<Administrador> administrador = query.getResultList().stream().findFirst();
        if (administrador.isPresent()) {
            return administrador.get();
        } else {
            return null;
        }

    }

}
