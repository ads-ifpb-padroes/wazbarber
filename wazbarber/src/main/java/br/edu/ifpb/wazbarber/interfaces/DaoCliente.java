package br.edu.ifpb.wazbarber.interfaces;

import br.edu.ifpb.wazbarber.model.Cliente;

/**
 *
 * @author jozimar
 */
public interface DaoCliente {

    public void cadastrar(Cliente cliente);

    public Cliente consultarPorEmail(String email);

    public Cliente autenticarCliente(String email, String senha);
    
//    public void atualizar(int idCliente, List<Agendamento> agendamentos);
    
    public void atualizar(Cliente cliente);
    
}
