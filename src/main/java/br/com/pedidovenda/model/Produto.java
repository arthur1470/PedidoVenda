package br.com.pedidovenda.model;

import br.com.pedidovenda.validation.SKU;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUTO")
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "NOME", nullable = false, length = 80)
    private String nome;

    @NotBlank @SKU
    @Column(name = "SKU", nullable = false, length = 20, unique = true)
    private String sku;

    @NotNull(message = "é obrigatório")
    @Column(name = "VALOR_UNITARIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorUnitario;

    @NotNull(message = "é obrigatório") @Min(0) @Max(value = 9999, message = "tem um valor muito alto")
    @Column(name = "QUANTIDADE_ESTOQUE", nullable = false, length = 5)
    private Integer quantidadeEstoque;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA_FK", nullable = false)
    private Categoria categoria;

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.toUpperCase();
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produto produto = (Produto) o;

        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
