package school.sptech.exemplo_relacionamento_jpa.repository.album;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.exemplo_relacionamento_jpa.model.album.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
