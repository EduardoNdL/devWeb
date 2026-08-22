package Exemplo_owasp.repository;

import Exemplo_owasp.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepositoryVulneravel {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepositoryVulneravel(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> buscarPorNome(String nome) {

        /*
         * VULNERABILIDADE:
         *
         * O valor recebido do usuário é concatenado diretamente
         * no comando SQL.
         *
         * Um usuário pode inserir caracteres especiais e alterar
         * a lógica da consulta.
         */
        String sql =
                "SELECT id, nome, descricao, preco " +
                        "FROM produto " +
                        "WHERE nome LIKE '%" + nome + "%'";

        /*
         * Para fins didáticos, exibimos o SQL que será executado.
         *
         * Em um sistema real, comandos SQL e dados sensíveis não
         * devem ser exibidos indiscriminadamente em logs.
         */
        System.out.println("SQL vulnerável: " + sql);

        return jdbcTemplate.query(
                sql,
                (resultado, numeroLinha) -> new Produto(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getString("descricao"),
                        resultado.getBigDecimal("preco")
                )
        );
    }
}
