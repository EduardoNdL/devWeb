package school.sptech.primeira.aula;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Request sempre no plural
@RequestMapping("/calculos")
public class CalculadoraController {
    @GetMapping("/somar/{n1}/{n2}")
    public Integer somar(@PathVariable Integer n1, @PathVariable Integer n2){
        return n1 + n2;
    }
}
