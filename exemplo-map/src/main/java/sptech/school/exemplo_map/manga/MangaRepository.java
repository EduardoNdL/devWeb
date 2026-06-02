package sptech.school.exemplo_map.manga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MangaRepository extends JpaRepository<Manga, Integer> {

    @Modifying
    @Query("DELETE FROM Manga m WHERE m.autor.id = :autorId")
    void deleteByAutorId(@Param("autorId") Integer autorId);
}
