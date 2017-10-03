package br.com.natura.fiap.naturatododia.entity;


public class Produto {
    private int id;
    private String nome;
    private String tipo;
    private String genero;
    private String cor;
    private String tom;
    private String img;
    private String link;
    private int fps;
    private boolean brilho;
    private float preco;

    public Produto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
