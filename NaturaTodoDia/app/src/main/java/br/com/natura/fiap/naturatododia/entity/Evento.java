package br.com.natura.fiap.naturatododia.entity;


public class Evento {
    private int id;
    private String nome;
    private Date nome;
    private String tipo;
    private String estacao;
    private String periodo;
    private String descricao;
    private Pessoa pessoa;

    @Override
    public String toString(){
        return this.nome;
    }

}
