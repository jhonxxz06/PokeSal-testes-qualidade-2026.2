package com.ucsal;

public class Mochila {
    private String item;
    private String item_descricao;
    private int quantidades;

    public Mochila(String item, String item_descricao, int quantidades) {
        this.item = item;
        this.item_descricao = item_descricao;
        this.quantidades = quantidades;
    }
    public void usarPocao(TreinadorPokesal treinador){
        System.out.println("Poção Selecionada...");
        treinador.getPokesal().setHP((treinador.getPokesal().getHP() + 5));
        System.out.println(treinador.getPokesal().getNome() + " teve 5 pnts de vida restaurados");
        treinador.setItensUso(treinador.getItensUso() + 1);
    }
    public void usarSuperPocao(TreinadorPokesal treinador){
        System.out.println("Super Poção Selecionada...");
        treinador.getPokesal().setHP((treinador.getPokesal().getHP() + 10));
        System.out.println(treinador.getPokesal().getNome() + " teve 10 pnts de vida restaurados");
        treinador.setItensUso(treinador.getItensUso() + 1);
    }

}
