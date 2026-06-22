package br.com.ebac.controller;

import br.com.ebac.dto.LoginDTO;
import br.com.ebac.util.CriptografiaUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    public static final Set<String> hashesAtivos = new HashSet<>();

    @PostMapping("/login")
    public String gerarTokenTeste(@RequestBody LoginDTO login) {
        String hash = CriptografiaUtil.gerarHash(login.getNomeLogin());
        hashesAtivos.add(hash);
        return hash;
    }
}