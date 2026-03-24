package school.sptech.exemplo_relacionamento_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exemplo_relacionamento_jpa.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
