package school.sptech.exemplo_relacionamento_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.exemplo_relacionamento_jpa.model.Musica;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {
    @Query("SELECT AVG(m.nota) FROM Musica m")
    Double getMedia();

    List<Musica> findByAlbumNome(String nome);
}
