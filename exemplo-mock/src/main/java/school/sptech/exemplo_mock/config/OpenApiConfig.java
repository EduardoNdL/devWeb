package school.sptech.exemplo_mock.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de simulacao de compra de dolar",
                version = "v1",
                description = "API didatica para CRUD de simulacoes simples de compra de dolar com cotacao externa.",
                contact = @Contact(name = "SPTech")
        ),
        servers = @Server(url = "/", description = "Servidor local")
)
public class OpenApiConfig {
}
