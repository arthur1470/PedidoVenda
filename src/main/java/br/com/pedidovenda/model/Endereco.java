package br.com.pedidovenda.model;

import javax.annotation.Generated;
import javax.naming.Name;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Endereco")
public class Endereco implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "LOGRADOURO", nullable = false, length = 150)
    private String logradouro;

    @Column(name = "NUMERO", nullable = false,length = 20)
    private String numero;

    @Column(name = "COMPLEMENTO", length = 150)
    private String complemento;

    @Column(name = "CIDADE", nullable = false, length = 60)
    private String cidade;

    @Column(name = "UF", nullable = false, length = 60)
    private String uf;

    @Column(name = "CEP", nullable = false, length = 9)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE_FK", nullable = false)
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        return id.equals(endereco.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
