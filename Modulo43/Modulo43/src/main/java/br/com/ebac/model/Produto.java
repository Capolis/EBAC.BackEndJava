package br.com.ebac.model;

public class Produto {
    
    private Integer id;
    private String nome;
    private Double preco;


    public Produto() {
    }

    public Produto(Integer id, String nome, Double preco) {
        setId(id);
        setNome(nome);
        setPreco(preco);
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}