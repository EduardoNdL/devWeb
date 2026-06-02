package school.sptech.exemplo_relacionamento_jpa.dto.album;

import java.time.LocalDate;
import java.util.List;

public class AlbumResponseDto {

    private Integer id;
    private String nome;
    private LocalDate dataLancamento;
    private List<MusicaAlbumDto> musicas;

    public AlbumResponseDto() {
    }

    public AlbumResponseDto(Integer id, String nome, LocalDate dataLancamento, List<MusicaAlbumDto> musicas) {
        this.id = id;
        this.nome = nome;
        this.dataLancamento = dataLancamento;
        this.musicas = musicas;
    }

    public static class MusicaAlbumDto{
        private Integer id;
        private String nome;

        public MusicaAlbumDto() {
        }

        public MusicaAlbumDto(Integer id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public List<MusicaAlbumDto> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<MusicaAlbumDto> musicas) {
        this.musicas = musicas;
    }
}
