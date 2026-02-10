package school.sptech.primeira.aula;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {
    private final List<Filme> l1 = new ArrayList<>(
                List.of(
                        new Filme("Titanic", "James Cameron"),
                        new Filme("Corra", "Jordan Pelee"))
    );

    @GetMapping
    public ResponseEntity<List<Filme>> listar(){
        return ResponseEntity.status(200).body(l1);
    }

    @GetMapping("/{index}")
    public ResponseEntity<Filme> buscarPorIndex(@PathVariable int index){
        if(isInvalidy(index)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(l1.get(index));
    }

    @DeleteMapping("/{index}")
    public ResponseEntity<Filme> remover(
            @PathVariable int index
    ){
        if(isInvalidy(index)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(410).body(l1.remove(index));
    }

    public boolean isInvalidy(int index){
        return index < 0 || index > l1.size() - 1;
    }

    @PostMapping()
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme novoFilme){
        l1.add(novoFilme);
        return ResponseEntity.status(201).body(novoFilme);
    }

    @PutMapping("/{index}")
    public Filme atualizar(@RequestBody Filme filmeParaAtualizar,
                           @PathVariable int index){
        if(isInvalidy(index)){
            return null;
        }

        l1.set(index, filmeParaAtualizar);
        return filmeParaAtualizar;
    }
}
