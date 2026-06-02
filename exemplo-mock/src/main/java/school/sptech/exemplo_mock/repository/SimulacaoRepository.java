package school.sptech.exemplo_mock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exemplo_mock.entity.SimulacaoEntity;

public interface SimulacaoRepository extends JpaRepository<SimulacaoEntity, Long> {
}
