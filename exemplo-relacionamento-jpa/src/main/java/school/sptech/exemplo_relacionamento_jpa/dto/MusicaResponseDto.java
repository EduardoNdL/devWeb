package school.sptech.exemplo_relacionamento_jpa.dto;

import school.sptech.exemplo_relacionamento_jpa.model.Album;

public class MusicaResponseDto {

    private Integer id;
    private String nome;
    private AlbumMusicaDto album;
    private Double nota;

    /*
    Nested classes
    Inner classes
    * */
    public static class AlbumMusicaDto{
        private Integer id;
        private String nome;

        public AlbumMusicaDto() {
        }

        public AlbumMusicaDto(Integer id, String nome) {
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

    public MusicaResponseDto() {
    }

    public MusicaResponseDto(Integer id, String nome, AlbumMusicaDto album, Double nota) {
        this.id = id;
        this.nome = nome;
        this.album = album;
        this.nota = nota;
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

    public AlbumMusicaDto getAlbum() {
        return album;
    }

    public void setAlbum(AlbumMusicaDto album) {
        this.album = album;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
