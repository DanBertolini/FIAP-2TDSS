package br.com.natura.fiap.naturatododia.entity;

public class Preferencia {
    private int id;
    private String nome;
    private String tomFavorito;
    private String fragFavorita;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTomFavorito() {
        return tomFavorito;
    }

    public void setTomFavorito(String tomFavorito) {
        this.tomFavorito = tomFavorito;
    }

    public String getFragFavorita() {
        return fragFavorita;
    }

    public void setFragFavorita(String fragFavorita) {
        this.fragFavorita = fragFavorita;
    }
}
