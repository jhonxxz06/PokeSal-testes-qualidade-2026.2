package com.ucsal;

public class TreinadorPokesal {
    private String nome;

    private Pokesal pokesal;

    private String cidade;

    private int itensUso = 0;

    private Mochila acessorios;

    public TreinadorPokesal(String nome, Pokesal pokesal, String cidade, Mochila acessorios) {
        this.nome = nome;
        this.pokesal = pokesal;
        this.cidade = cidade;
        this.acessorios = acessorios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pokesal getPokesal() {
        return pokesal;
    }

    public void setPokesal(Pokesal pokesal) {
        this.pokesal = pokesal;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getItensUso() {
        return itensUso;
    }

    public void setItensUso(int itensUso) {
        this.itensUso = itensUso;
    }

    public Mochila getAcessorios() {
        return acessorios;
    }

    public void setAcessorios(Mochila acessorios) {
        this.acessorios = acessorios;
    }
}
