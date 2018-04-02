package br.edu.ifpb.wazbarber.builder;

import br.edu.ifpb.wazbarber.model.Cliente;

/**
 *
 * @author jozimar
 */
public class ClienteBuilder {

    private String nomeCompleto;
    private String apelido;
    private String celular;
    private String email;
    private String senha;
    private String cidade;

    public ClienteBuilder() {
    }

    public ClienteBuilder comNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public ClienteBuilder comApelido(String apelido) {
        this.apelido = apelido;
        return this;
    }

    public ClienteBuilder comCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public ClienteBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public ClienteBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public ClienteBuilder comCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public Cliente toCliente() throws ClienteBuilderException {
        validarCliente();
        return new Cliente(nomeCompleto, apelido, celular, email, senha, cidade);
    }

    private void validarCliente() throws ClienteBuilderException {

        if (email == null || email.isEmpty()) {
            throw new ClienteBuilderException("O e-mail é obrigatório");
        }
        if (senha == null || senha.isEmpty()) {
            throw new ClienteBuilderException("A senha é obrigatória");
        }
    }
}
