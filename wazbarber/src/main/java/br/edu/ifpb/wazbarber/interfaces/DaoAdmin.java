package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.Administrador;

/**
 *
 * @author jozimar
 */
public interface DaoAdmin {

    public Administrador consultarPorLogin(String login);

    public Administrador autenticarAdmin(String login, String senha);
}
