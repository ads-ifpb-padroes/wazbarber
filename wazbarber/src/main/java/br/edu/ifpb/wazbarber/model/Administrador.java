package br.edu.ifpb.wazbarber.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author romulo
 */
@Entity
public class Administrador implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Administrador() {

    }

    public Administrador(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Administrador{" + "id=" + id + ", nome=" + nome 
                + ", login=" + login + ", senha=" + senha + '}';
    }
}
