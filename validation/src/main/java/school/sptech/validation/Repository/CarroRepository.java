package school.sptech.validation.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.validation.Entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Integer> {
}
