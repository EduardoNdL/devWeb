package school.sptech.exemplo_jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HeroiRepository extends JpaRepository<Heroi, Integer> {
    List<Heroi> findByForcaGreaterThan(int maior);

    List<Heroi> findByNomeContainingIgnoreCase(String nome);
}
