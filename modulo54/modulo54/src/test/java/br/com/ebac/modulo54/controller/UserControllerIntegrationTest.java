package br.com.ebac.modulo54.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.ebac.modulo54.model.Usuario;
import br.com.ebac.modulo54.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc // criando e injetando o MockMvc automaticamente
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldReturnUserDetailsWhenUserExists() throws Exception {
        // Preparação: Criamos e salvamos o usuário no H2 apenas para este teste.
        Usuario testUser = new Usuario();
        testUser.setName("Caio");
        Usuario savedUser = userRepository.save(testUser); // O save já retorna o objeto com o ID gerado

        // Ação e Verificação (Act & Assert): Simulamos o GET e validamos o retorno
        mockMvc.perform(get("/users/" + savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedUser.getId()))
                .andExpect(jsonPath("$.name").value("Caio"));
    }

    @Test
    void shouldReturn404WhenUserDoesNotExist() throws Exception {
        // Apenas simulamos a requisição para um ID aleatório (ex: 999).
        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }
}