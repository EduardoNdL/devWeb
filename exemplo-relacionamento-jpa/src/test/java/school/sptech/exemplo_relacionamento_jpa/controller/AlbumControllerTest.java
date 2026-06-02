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
import school.sptech.exemplo_relacionamento_jpa.dto.album.AlbumRequestDto;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@DisplayName("AlbumController - Testes de Integração")
class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("GET /albuns → deve retornar 200 e a lista com todos os álbuns cadastrados")
    void listarTodos_deveRetornar200EListaComTresAlbuns() throws Exception {
        mockMvc.perform(get("/albuns"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3)))           // data-test.sql insere 3 álbuns
                .andExpect(jsonPath("$[0].nome").value("Thriller"));
    }


    @Test
    @DisplayName("GET /albuns/{id} → deve retornar 200 e os dados corretos do álbum")
    void buscarPorId_deveRetornar200EAlbumCorreto() throws Exception {
        mockMvc.perform(get("/albuns/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Thriller"))
                .andExpect(jsonPath("$.dataLancamento").value("1982-11-30"));
    }

    @Test
    @DisplayName("GET /albuns/{id} → deve retornar 404 quando o álbum não existe")
    void buscarPorId_deveRetornar404QuandoIdNaoExiste() throws Exception {
        mockMvc.perform(get("/albuns/999"))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("POST /albuns → deve retornar 201 e o álbum criado quando os dados são válidos")
    void cadastrar_deveRetornar201EAlbumCriado() throws Exception {
        AlbumRequestDto dto = new AlbumRequestDto("The Wall", LocalDate.of(1979, 11, 30));

        mockMvc.perform(post("/albuns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.nome").value("The Wall"))
                .andExpect(jsonPath("$.dataLancamento").value("1979-11-30"));
    }

    @Test
    @DisplayName("POST /albuns → deve retornar 400 quando o nome está em branco (@NotBlank)")
    void cadastrar_deveRetornar400QuandoNomeEmBranco() throws Exception {
        AlbumRequestDto dto = new AlbumRequestDto("", LocalDate.of(1979, 11, 30));

        mockMvc.perform(post("/albuns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /albuns → deve retornar 400 quando a data de lançamento é futura (@PastOrPresent)")
    void cadastrar_deveRetornar400QuandoDataLancamentoEFutura() throws Exception {
        AlbumRequestDto dto = new AlbumRequestDto("Album do Futuro", LocalDate.now().plusYears(1));

        mockMvc.perform(post("/albuns")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("PUT /albuns/{id} → deve retornar 200 e refletir os novos dados do álbum")
    void atualizar_deveRetornar200EAlbumAtualizado() throws Exception {
        AlbumRequestDto dto = new AlbumRequestDto("Thriller (Edição Deluxe)", LocalDate.of(1982, 11, 30));

        mockMvc.perform(put("/albuns/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Thriller (Edição Deluxe)"));
    }

    @Test
    @DisplayName("PUT /albuns/{id} → deve retornar 404 quando o álbum a atualizar não existe")
    void atualizar_deveRetornar404QuandoIdNaoExiste() throws Exception {
        AlbumRequestDto dto = new AlbumRequestDto("Qualquer Nome", LocalDate.of(2000, 1, 1));

        mockMvc.perform(put("/albuns/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /albuns/{id} → deve retornar 204 e remover o álbum junto com todas as suas músicas")
    void deletar_deveRetornar204ERemoverAlbumEMusicas() throws Exception {
        mockMvc.perform(delete("/albuns/2"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/albuns/2"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/musicas/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /albuns/{id} → deve retornar 404 quando o álbum a deletar não existe")
    void deletar_deveRetornar404QuandoIdNaoExiste() throws Exception {
        mockMvc.perform(delete("/albuns/999"))
                .andExpect(status().isNotFound());
    }
}
