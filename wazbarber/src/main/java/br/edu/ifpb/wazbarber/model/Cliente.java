package br.edu.ifpb.wazbarber.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author romulo
 */
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String nomeCompleto;
    private String apelido;
    private String celular;
    private String email;
    private String senha;
    private String cidade;

//    @OneToMany(mappedBy = "cliente")
    @OneToMany
    private List<Agendamento> agendamentos;

    public Cliente() {
    }

    public Cliente(int id, String nomeCompleto, String apelido,
            String celular, String email, String senha, String cidade,
            List<Agendamento> agendamentos) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.celular = celular;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
        this.agendamentos = agendamentos;
    }

    public int getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getCidade() {
        return cidade;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nomeCompleto=" + nomeCompleto
                + ", apelido=" + apelido + ", celular=" + celular
                + ", email=" + email + ", senha=" + senha + ", cidade=" + cidade + '}';
    }
}
