// Classe alterado de User para Usuario para evitar conflito com a classe User do Spring Security, 
// sem esta alteração o teste de integração não funcionaria, pois o Spring Security iria tentar injetar a 
// classe User do Spring Security e não a classe Usuario que criamos. pqp
package br.com.ebac.modulo54.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Construtores
    public Usuario() {
    }

    public Usuario(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}