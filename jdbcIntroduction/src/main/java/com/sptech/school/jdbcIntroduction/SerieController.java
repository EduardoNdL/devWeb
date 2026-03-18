package com.sptech.school.jdbcIntroduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/series")
public class SerieController {

    // Injenção de dependências...
//    @Autowired
    private final JdbcTemplate template;
    public SerieController(JdbcTemplate template) {
        this.template = template;
    }

    /* .query -> retorna uma lista
    * .update -> alterações em geral
    * .queryForObject -> retorna uma ocorrencia*/

    @GetMapping
    public ResponseEntity<List<Serie>> listar(){
        String query = "SELECT * FROM serie;";

        List<Serie> series = template.query(query, new BeanPropertyRowMapper<>(Serie.class));

        if(series.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(series);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Serie> filmePorId(@PathVariable UUID uuid){
        String query = "SELECT * FROM serie WHERE id = ?";
        UUID[] args = new UUID[]{uuid};

        List<Serie> serie = template.query(query, args, new BeanPropertyRowMapper<>(Serie.class));

        return serie.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.status(200).body(serie.get(0));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<String> deletarFilme(@PathVariable UUID uuid){
        String query = "DELETE FROM serie WHERE id = ?";

        Integer rows = template.update(query, uuid);
        return rows == 1 ? ResponseEntity.status(200).build() : ResponseEntity.status(404).build();
    }

    @PostMapping()
    public ResponseEntity<String> criarFilme(@RequestBody Serie serie){
        String query = "INSERT INTO serie (id, nome, plataforma, nota) VALUES " +
                "(?,?,?,?)";

        Integer rows = template.update(query, serie.getId(), serie.getNome(), serie.getPlataforma(),
        serie.getNota());
        return rows == 1 ? ResponseEntity.status(201).build() : ResponseEntity.status(404).build();
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Serie>> filtrar (@RequestParam String nome){
        String query = "SELECT * FROM serie WHERE nome LIKE LOWER(%?%);";

        List<Serie> series = template.query(query, new BeanPropertyRowMapper<>(Serie.class), nome.toLowerCase());

        return series.isEmpty() ? ResponseEntity.status(200).body(series) : ResponseEntity.status(404).build();
    }
}
