package br.com.ebac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebac.dto.UsuarioDTO;
import br.com.ebac.model.Usuario;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // Simulação de banco de dados para garantir a idempotência
    private final Map<String, Usuario> bankMock = new HashMap<>();

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(
            @RequestHeader(value = "X-Hash-Teste", required = false) String hashTeste,
            @Valid @RequestBody UsuarioDTO dto) {

        // Validação da possibilidade de fazer os testes usando o hash
        if (hashTeste == null || !AutenticacaoController.hashesAtivos.contains(hashTeste)) {
            throw new SecurityException("Hash de teste inválido ou ausente. Faça o login primeiro.");
        }

        // Idempotência: Se o usuário (chave primária 'nome') já existe, retorna o mesmo status e os mesmos dados
        if (bankMock.containsKey(dto.getNome())) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bankMock.get(dto.getNome()));
        }

        Usuario novoUsuario = new Usuario(dto.getNome(), dto.getIdade());
        bankMock.put(dto.getNome(), novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(
            @RequestHeader(value = "X-Hash-Teste", required = false) String hashTeste) {

        // Mesma validação do hash de teste exigida para a criação
        validarHashTeste(hashTeste);

        // Retorna todos os usuários guardados na memória
        List<Usuario> listaUsuarios = new ArrayList<>(bankMock.values());

        return ResponseEntity.ok(listaUsuarios);
    }

    private void validarHashTeste(String hashTeste) {
        if (hashTeste == null || !AutenticacaoController.hashesAtivos.contains(hashTeste)) {
            throw new SecurityException("Hash de teste inválido ou ausente. Faça o login primeiro.");
        }
    }
}