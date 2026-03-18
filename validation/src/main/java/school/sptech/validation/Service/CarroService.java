package school.sptech.validation.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import school.sptech.validation.Dto.CarroMapper;
import school.sptech.validation.Dto.CarroResponse;
import school.sptech.validation.EntidadeNaoEncontradaException;
import school.sptech.validation.Entity.Carro;
import school.sptech.validation.Repository.CarroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public List<Carro> listar(){
        return repository.findAll();
    }

    public Carro buscarPorId(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Carro não encontrado"));
    }

    public Carro cadastrar(Carro carro){
        return repository.save(carro);
    }
}
