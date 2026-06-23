package br.com.ebac.modulo54.service;

import org.springframework.stereotype.Service;

import br.com.ebac.modulo54.exception.UserNotFoundException;
import br.com.ebac.modulo54.model.Usuario;
import br.com.ebac.modulo54.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Injeção de dependência via construtor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario getUserById(Long id) {
        // Busca o usuário; se não encontrar, lança a exceção personalizada
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado."));
    }
}