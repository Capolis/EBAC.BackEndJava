package br.com.ebac.modulo54.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ebac.modulo54.exception.UserNotFoundException;
import br.com.ebac.modulo54.model.Usuario;
import br.com.ebac.modulo54.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // Testando Exceções
    @Test
    void shouldThrowUserNotFoundExceptionWhenIdDoesNotExist() {
        // Configurando o mock para retornar vazio
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Verificando se a exceção correta é lançada
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99L));
        
        // Verificando se o repositório foi de fato chamado com o ID 99
        verify(userRepository).findById(99L);
    }

    // Criando Mock e validando chamada
    @Test
    void shouldReturnUserWhenIdExists() {
        // Criando o usuário fictício
        Usuario mockUser = new Usuario(1L, "Caio César");
        
        // Simulando a resposta do repositório
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        // Chamando o método do serviço
        Usuario result = userService.getUserById(1L);

        // Asserts para garantir que os dados vieram corretos
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Caio César", result.getName());

        // Aprofundando no verify: garantindo que o método foi chamado exatamente 1 vez com o ID 1
        verify(userRepository, times(1)).findById(1L);
    }
}