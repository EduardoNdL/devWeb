package school.sptech.exemplo_jpa;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/herois")
public class HeroiController {

    private final HeroiRepository repository;

    public HeroiController(HeroiRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public ResponseEntity<List<Heroi>> listagem(){
        List<Heroi> reponse = repository.findAll();

        return reponse.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.status(200).body(reponse);
    }

    @PostMapping()
    public ResponseEntity<Heroi> cadastrar(@RequestBody HeroiRequestDto heroi){
        Heroi paraRegistrar = new Heroi(heroi);
        Heroi response = repository.save(paraRegistrar);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Heroi> buscarPorId(@PathVariable int id){
        Optional<Heroi> response = repository.findById(id);
        return
                response.isEmpty() ? ResponseEntity.status(404).build() :
                ResponseEntity.status(200).body(response.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroi> alterarPorId(@RequestBody HeroiRequestDto heroi, @PathVariable int id){
        if(repository.existsById(id)){
            return ResponseEntity.status(404).build();
        }

        Heroi paraRegistrar = new Heroi(heroi);
        paraRegistrar.setId(id);

        Heroi response = repository.save(paraRegistrar);

        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable int id){
        if(repository.existsById(id)){
            return ResponseEntity.status(404).build();
        }

        repository.deleteById(id);

        return ResponseEntity.status(204).build();
    }

    @GetMapping("/forca")
    public ResponseEntity<List<Heroi>> buscarPorParametro(@RequestParam int maior){
        List<Heroi> response = repository.findByForcaGreaterThan(maior);

        return response.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(response);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<Heroi>> buscarPorParametroContendo(@RequestParam String nome){
        List<Heroi> response = repository.findByNomeContainingIgnoreCase(nome);

        return response.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(response);
    }
}
