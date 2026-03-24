package school.sptech.exemplo_relacionamento_jpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testes")
public class TesteController {
    @GetMapping
    public ResponseEntity<String> teste(){
     return ResponseEntity.ok("Hello World");
    }}
