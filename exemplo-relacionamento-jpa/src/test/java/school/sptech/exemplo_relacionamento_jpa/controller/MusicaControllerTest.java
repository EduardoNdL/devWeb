package school.sptech.exemplo_relacionamento_jpa.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import school.sptech.exemplo_relacionamento_jpa.dto.musica.MusicaRequestDto;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@DisplayName("MusicaController - Testes de Integração")
class MusicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("GET /musicas → deve retornar 200 e a lista com todas as músicas cadastradas")
    void listarTodas_deveRetornar200EListaComQuatroMusicas() throws Exception {
        mockMvc.perform(get("/musicas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    @DisplayName("GET /musicas/{id} → deve retornar 200 e o objeto aninhado do álbum dentro da música")
    void buscarPorId_deveRetornar200ComAlbumAninhado() throws Exception {
        mockMvc.perform(get("/musicas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Billie Jean"))
                .andExpect(jsonPath("$.nota").value(9.7))
                .andExpect(jsonPath("$.album.id").value(1))
                .andExpect(jsonPath("$.album.nome").value("Thriller"));
    }

    @Test
    @DisplayName("GET /musicas/{id} → deve retornar 404 quando a música não existe")
    void buscarPorId_deveRetornar404QuandoNaoEncontrada() throws Exception {
        mockMvc.perform(get("/musicas/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /musicas → deve retornar 201 e associar corretamente o álbum informado")
    void cadastrar_deveRetornar201EAssociarAlbum() throws Exception {
        MusicaRequestDto dto = new MusicaRequestDto("Human Nature", 1, 8.5);

        mockMvc.perform(post("/musicas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Human Nature"))
                .andExpect(jsonPath("$.nota").value(8.5))
                // Confirma que o relacionamento foi resolvido e retornado no response
                .andExpect(jsonPath("$.album.id").value(1))
                .andExpect(jsonPath("$.album.nome").value("Thriller"));
    }

    @Test
    @DisplayName("POST /musicas → deve retornar 404 quando o albumId informado não existe no banco")
    void cadastrar_deveRetornar404QuandoAlbumNaoExiste() throws Exception {
        MusicaRequestDto dto = new MusicaRequestDto("Musica Órfã", 999, 7.0);

        mockMvc.perform(post("/musicas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /musicas → deve retornar 400 quando o nome da música está em branco (@NotBlank)")
    void cadastrar_deveRetornar400QuandoNomeEmBranco() throws Exception {
        MusicaRequestDto dto = new MusicaRequestDto("", 1, 8.0);

        mockMvc.perform(post("/musicas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /musicas → deve retornar 400 quando a nota está fora do intervalo [1.0, 10.0]")
    void cadastrar_deveRetornar400QuandoNotaForaDosLimites() throws Exception {
        MusicaRequestDto dto = new MusicaRequestDto("Nota Inválida", 1, 11.0);

        mockMvc.perform(post("/musicas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /musicas/media → deve retornar 200 e a média correta das notas (JPQL com AVG)")
    void buscarMedia_deveRetornar200EMediaCorreta() throws Exception {
        mockMvc.perform(get("/musicas/media"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", closeTo(9.25, 0.01)));
    }

    @Test
    @DisplayName("GET /musicas/albuns?nome= → deve retornar 200 e apenas as músicas do álbum filtrado (Query Method)")
    void buscarPorNomeAlbum_deveRetornar200ComMusicasDoAlbum() throws Exception {
        mockMvc.perform(get("/musicas/albuns").param("nome", "Thriller"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].album.nome").value("Thriller"))
                .andExpect(jsonPath("$[1].album.nome").value("Thriller"));
    }

    @Test
    @DisplayName("GET /musicas/albuns?nome= → deve retornar 204 quando nenhuma música corresponde ao álbum")
    void buscarPorNomeAlbum_deveRetornar204QuandoSemResultados() throws Exception {
        mockMvc.perform(get("/musicas/albuns").param("nome", "Album Inexistente"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /musicas/albuns/jpql?nome= → @Query JPQL deve retornar o mesmo resultado que o Query Method")
    void buscarPorNomeAlbumJpql_deveRetornarMesmoResultadoQueQueryMethod() throws Exception {
        mockMvc.perform(get("/musicas/albuns/jpql").param("nome", "Thriller"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].album.nome").value("Thriller"));
    }
}
