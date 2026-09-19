package com.ucsal;

public class Mochila {
    private String item;

    private String itemDescricao;

    private int quantidades;

    public Mochila(String item, String itemDescricao, int quantidades) {
        this.item = item;
        this.itemDescricao = itemDescricao;
        this.quantidades = quantidades;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemDescricao() {
        return itemDescricao;
    }

    public void setItemDescricao(String itemDescricao) {
        this.itemDescricao = itemDescricao;
    }

    public int getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(int quantidades) {
        this.quantidades = quantidades;
    }

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
