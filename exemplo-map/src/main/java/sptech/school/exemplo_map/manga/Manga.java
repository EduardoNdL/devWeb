package sptech.school.exemplo_map.manga;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sptech.school.exemplo_map.autor.Autor;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Manga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private LocalDate dataPublicacao;
    private String editora;
    private String genero;
    private Integer volumes;

    @ManyToOne
    private Autor autor;
}
