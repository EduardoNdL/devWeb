package school.sptech.validation.Controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.validation.Dto.CarroMapper;
import school.sptech.validation.Dto.CarroRequest;
import school.sptech.validation.Dto.CarroResponse;
import school.sptech.validation.Entity.Carro;
import school.sptech.validation.Repository.CarroRepository;
import school.sptech.validation.Service.CarroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {
    private final CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<CarroResponse>> listar(){
        List<Carro> response = service.listar();

        if(response.isEmpty()){
            ResponseEntity.noContent().build();
        }

        List<CarroResponse> data = CarroMapper.toResponseDto(response);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroResponse> buscarPorId(@PathVariable Integer id){
        Carro carro = service.buscarPorId(id);

        CarroResponse data = CarroMapper.toResponseDto(carro);
        return ResponseEntity.ok(data);
    }

    @PostMapping()
    public ResponseEntity<CarroResponse> cadastrar(@Valid @RequestBody CarroRequest carro){
        Carro request = CarroMapper.toCarModel(carro);
        CarroResponse response = CarroMapper.toResponseDto(service.cadastrar(request));

        return ResponseEntity.created(null).body(response);
    }
}
