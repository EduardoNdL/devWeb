package school.sptech.exemplo_relacionamento_jpa.repository.musica;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.sptech.exemplo_relacionamento_jpa.model.musica.Musica;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Integer> {

    @Query("SELECT COALESCE(AVG(m.nota), 0) FROM Musica m")
    Double getMedia();

    List<Musica> findByAlbumNome(String nome);

    @Query("SELECT m FROM Musica m WHERE m.album.nome = :nome")
    List<Musica> buscarPorNomeDoAlbum(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query("DELETE FROM Musica m WHERE m.album.id = :id")
    void removerMusicasPorIdAlbum(@Param("id") Integer id);
}
