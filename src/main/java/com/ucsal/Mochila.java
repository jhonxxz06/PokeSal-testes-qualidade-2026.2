package com.ucsal;

/**
 * Item da mochila do treinador, com nome, descrição e quantidade disponível.
 */
public class Mochila {

    /**
     * Nome do item.
     */
    private String item;

    /**
     * Descrição do item.
     */
    private String itemDescricao;

    /**
     * Quantidade de unidades disponíveis do item.
     */
    private int quantidades;

    /**
     * Cria um item da mochila.
     *
     * @param item          nome do item
     * @param itemDescricao descrição do item
     * @param quantidades   quantidade de unidades disponíveis
     */
    public Mochila(String item, String itemDescricao, int quantidades) {
        this.item = item;
        this.itemDescricao = itemDescricao;
        this.quantidades = quantidades;
    }

    /**
     * Retorna o nome do item.
     *
     * @return nome do item
     */
    public String getItem() {
        return item;
    }

    /**
     * Altera o nome do item.
     *
     * @param item novo nome do item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * Retorna a descrição do item.
     *
     * @return descrição do item
     */
    public String getItemDescricao() {
        return itemDescricao;
    }

    /**
     * Altera a descrição do item.
     *
     * @param itemDescricao nova descrição do item
     */
    public void setItemDescricao(String itemDescricao) {
        this.itemDescricao = itemDescricao;
    }

    /**
     * Retorna a quantidade de unidades disponíveis.
     *
     * @return quantidade de unidades
     */
    public int getQuantidades() {
        return quantidades;
    }

    /**
     * Altera a quantidade de unidades disponíveis.
     *
     * @param quantidades nova quantidade de unidades
     */
    public void setQuantidades(int quantidades) {
        this.quantidades = quantidades;
    }

    /**
     * Usa uma poção comum, recuperando pontos de vida do Pokesal do treinador.
     *
     * @param treinador treinador que está usando a poção
     * @param pocao     item da mochila que será consumido
     */
    public void usarPocao(TreinadorPokesal treinador, Mochila pocao) {
        // ver um verificador de poção
        if (pocao.getQuantidades() > 0) {
            System.out.println("Poção Selecionada...");
            treinador.getPokesal().setHpBatalha((treinador.getPokesal().getHpBatalha() + 5));
            System.out.println(treinador.getPokesal().getNome()
                    + " teve 5 pnts de vida restaurados");
            treinador.setItensUso(treinador.getItensUso() + 1);
            pocao.setQuantidades(getQuantidades() - 1);
            // pensar para diminuição da quantidade dos itens
        } else {
            System.out.println(getItem() + " Indisponível");
        }
    }

    /**
     * Usa uma poção de fúria: o Pokesal ganha 10% de força e 20% de velocidade por um turno.
     *
     * @param treinador  treinador que está usando a poção de fúria
     * @param pocaoFuria item da mochila que será consumido
     */
    public void usarPocaoFuria(TreinadorPokesal treinador, Mochila pocaoFuria) {
        if (pocaoFuria.getQuantidades() > 0) {
            System.out.println("Poção de Fúria Selecionada...");
            treinador.getPokesal().aplicarPocaoFuria();
            treinador.setItensUso(treinador.getItensUso() + 1);
            pocaoFuria.setQuantidades(pocaoFuria.getQuantidades() - 1);
        } else {
            System.out.println(getItem() + " Indisponível");
        }
    }

    /**
     * Usa uma poção de força: o Pokesal ganha 25% de força por um turno.
     *
     * @param treinador  treinador que está usando a poção de força
     * @param pocaoForca item da mochila que será consumido
     */
    public void usarPocaoForca(TreinadorPokesal treinador, Mochila pocaoForca) {
        if (pocaoForca.getQuantidades() > 0) {
            System.out.println("Poção de Força Selecionada...");
            treinador.getPokesal().aplicarPocaoForca();
            treinador.setItensUso(treinador.getItensUso() + 1);
            pocaoForca.setQuantidades(pocaoForca.getQuantidades() - 1);
        } else {
            System.out.println(getItem() + " Indisponível");
        }
    }

    /**
     * Usa uma super poção, recuperando mais pontos de vida do Pokesal do treinador.
     *
     * @param treinador  treinador que está usando a super poção
     * @param superPocao item da mochila que será consumido
     */
    public void usarSuperPocao(TreinadorPokesal treinador, Mochila superPocao) {
        if (superPocao.getQuantidades() > 0) {
            System.out.println("Super Poção Selecionada...");
            treinador.getPokesal().setHpBatalha((treinador.getPokesal().getHpBatalha() + 10));
            System.out.println(treinador.getPokesal().getNome()
                    + " teve 10 pnts de vida restaurados");
            treinador.setItensUso(treinador.getItensUso() + 1);
            superPocao.setQuantidades(getQuantidades() - 1);
            // pensar para diminuição da quantidade dos itens
        } else {
            System.out.println(getItem() + " Indisponível");
        }
    }
}
