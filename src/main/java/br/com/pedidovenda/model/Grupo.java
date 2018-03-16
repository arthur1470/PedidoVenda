package br.com.pedidovenda.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "GRUPO")
public class Grupo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 40)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 80)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo grupo = (Grupo) o;

        return id.equals(grupo.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
