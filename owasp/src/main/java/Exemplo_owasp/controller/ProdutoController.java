package Exemplo_owasp.controller;

import Exemplo_owasp.model.Produto;
import Exemplo_owasp.repository.ProdutoRepositoryVulneravel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepositoryVulneravel repositoryVulneravel;

    public ProdutoController(
            ProdutoRepositoryVulneravel repositoryVulneravel) {

        this.repositoryVulneravel = repositoryVulneravel;
    }

    /*
     * Endpoint propositalmente vulnerável.
     *
     * Deve ser usado somente para demonstração local.
     */
    @GetMapping("/vulneravel")
    public ResponseEntity<List<Produto>> buscarVulneravel(
            @RequestParam String nome) {

        List<Produto> produtos =
                repositoryVulneravel.buscarPorNome(nome);

        return ResponseEntity.ok(produtos);
    }

}