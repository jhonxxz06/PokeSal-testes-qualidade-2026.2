package com.ucsal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MochilaTest {

    private TreinadorPokesal criarTreinador(Mochila mochila) {
        Pokesal pokesal = new Pokesal("Teste", 50, "AtkA", "AtkB", 10, ElementosTipagem.FOGO);
        return new TreinadorPokesal("Treinador", pokesal, "Salvador", mochila);
    }

    @Test
    @DisplayName("Usar Pocao de Furia com estoque disponivel ativa o efeito e consome estoque")
    void pocaoFuriaComEstoque() {
        Mochila pocao = new Mochila("Poção de Fúria", "aumenta força e velocidade", 2);
        TreinadorPokesal treinador = criarTreinador(pocao);

        pocao.usarPocaoFuria(treinador, pocao);

        assertAll(
                () -> assertEquals(1, pocao.getQuantidades()),
                () -> assertEquals(1, treinador.getItensUso()),
                () -> assertEquals(1.10, treinador.getPokesal().getForcaEfeito(), 0.0001)
        );
    }

    @Test
    @DisplayName("Usar Pocao de Furia sem estoque nao ativa efeito nem consome nada")
    void pocaoFuriaSemEstoque() {
        Mochila pocao = new Mochila("Poção de Fúria", "aumenta força e velocidade", 0);
        TreinadorPokesal treinador = criarTreinador(pocao);

        pocao.usarPocaoFuria(treinador, pocao);

        assertAll(
                () -> assertEquals(0, pocao.getQuantidades()),
                () -> assertEquals(0, treinador.getItensUso()),
                () -> assertEquals(1.0, treinador.getPokesal().getForcaEfeito(), 0.0001)
        );
    }

    @Test
    @DisplayName("Usar Pocao de Forca com estoque disponivel ativa o efeito e consome estoque")
    void pocaoForcaComEstoque() {
        Mochila pocao = new Mochila("Poção de Força", "aumenta força", 3);
        TreinadorPokesal treinador = criarTreinador(pocao);

        pocao.usarPocaoForca(treinador, pocao);

        assertAll(
                () -> assertEquals(2, pocao.getQuantidades()),
                () -> assertEquals(1, treinador.getItensUso()),
                () -> assertEquals(1.25, treinador.getPokesal().getForcaEfeito(), 0.0001)
        );
    }

    @Test
    @DisplayName("Usar Pocao de Forca sem estoque nao ativa efeito nem consome nada")
    void pocaoForcaSemEstoque() {
        Mochila pocao = new Mochila("Poção de Força", "aumenta força", 0);
        TreinadorPokesal treinador = criarTreinador(pocao);

        pocao.usarPocaoForca(treinador, pocao);

        assertAll(
                () -> assertEquals(0, pocao.getQuantidades()),
                () -> assertEquals(0, treinador.getItensUso()),
                () -> assertEquals(1.0, treinador.getPokesal().getForcaEfeito(), 0.0001)
        );
    }
}
