package br.com.ebac.modulo54.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ebac.modulo54.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}