package Exemplo_owasp.repository;

import Exemplo_owasp.model.Produto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdutoRepositorySeguro {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepositorySeguro(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Produto> buscarPorNome(String nome) {

        /*
         * CORREÇÃO:
         *
         * O comando SQL utiliza um marcador de parâmetro.
         *
         * O valor não é concatenado diretamente na consulta.
         */
        String sql = """
                SELECT id, nome, descricao, preco
                FROM produto
                WHERE LOWER(nome) LIKE LOWER(?)
                """;

        /*
         * O valor informado pelo usuário é enviado separadamente.
         *
         * O driver JDBC entende que este conteúdo é um valor,
         * e não uma parte executável do comando SQL.
         */
        String parametro = "%" + nome + "%";

        return jdbcTemplate.query(
                sql,
                (resultado, numeroLinha) -> new Produto(
                        resultado.getLong("id"),
                        resultado.getString("nome"),
                        resultado.getString("descricao"),
                        resultado.getBigDecimal("preco")
                ),
                parametro
        );
    }
}
