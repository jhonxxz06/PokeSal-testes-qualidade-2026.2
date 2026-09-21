package com.ucsal;

/**
 * Treinador de Pokesal, com seu Pokesal, cidade, mochila e itens já usados.
 */
public class TreinadorPokesal {

    /** Nome do treinador. */
    private String nome;

    /** Pokesal do treinador. */
    private Pokesal pokesal;

    /** Cidade de origem do treinador. */
    private String cidade;

    /** Quantidade de itens já usados pelo treinador. */
    private int itensUso = 0;

    /** Mochila (item) do treinador. */
    private Mochila acessorios;

    /**
     * Cria um treinador.
     *
     * @param nome nome do treinador
     * @param pokesal Pokesal do treinador
     * @param cidade cidade de origem do treinador
     * @param acessorios mochila do treinador
     */
    public TreinadorPokesal(String nome, Pokesal pokesal, String cidade, Mochila acessorios) {
        this.nome = nome;
        this.pokesal = pokesal;
        this.cidade = cidade;
        this.acessorios = acessorios;
    }

    /**
     * Retorna o nome do treinador.
     *
     * @return nome do treinador
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do treinador.
     *
     * @param nome novo nome do treinador
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o Pokesal do treinador.
     *
     * @return Pokesal do treinador
     */
    public Pokesal getPokesal() {
        return pokesal;
    }

    /**
     * Altera o Pokesal do treinador.
     *
     * @param pokesal novo Pokesal do treinador
     */
    public void setPokesal(Pokesal pokesal) {
        this.pokesal = pokesal;
    }

    /**
     * Retorna a cidade de origem do treinador.
     *
     * @return cidade do treinador
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Altera a cidade de origem do treinador.
     *
     * @param cidade nova cidade do treinador
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Retorna a quantidade de itens já usados.
     *
     * @return quantidade de itens usados
     */
    public int getItensUso() {
        return itensUso;
    }

    /**
     * Altera a quantidade de itens já usados.
     *
     * @param itensUso nova quantidade de itens usados
     */
    public void setItensUso(int itensUso) {
        this.itensUso = itensUso;
    }

    /**
     * Retorna a mochila do treinador.
     *
     * @return mochila do treinador
     */
    public Mochila getAcessorios() {
        return acessorios;
    }

    /**
     * Altera a mochila do treinador.
     *
     * @param acessorios nova mochila do treinador
     */
    public void setAcessorios(Mochila acessorios) {
        this.acessorios = acessorios;
    }
}
