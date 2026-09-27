package com.ucsal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementosTipagemTest {

    private static final double DELTA = 0.0001;

    @Test
    @DisplayName("Mesmo tipo contra mesmo tipo nao aplica multiplicador")
    void mesmoTipoNaoTemMultiplicador() {
        double dano = ElementosTipagem.FOGO.calculoEfetividade(ElementosTipagem.FOGO);
        assertEquals(12.0, dano, DELTA);
    }

    @Test
    @DisplayName("Fogo dobra o dano contra Planta")
    void fogoDobraDanoContraPlanta() {
        double dano = ElementosTipagem.FOGO.calculoEfetividade(ElementosTipagem.PLANTA);
        assertEquals(24.0, dano, DELTA);
    }

    @Test
    @DisplayName("Fogo perde pela metade contra Agua")
    void fogoPerdePraAgua() {
        double dano = ElementosTipagem.FOGO.calculoEfetividade(ElementosTipagem.AGUA);
        assertEquals(6.0, dano, DELTA);
    }

    @Test
    @DisplayName("Agua dobra o dano contra Fogo")
    void aguaDobraDanoContraFogo() {
        double dano = ElementosTipagem.AGUA.calculoEfetividade(ElementosTipagem.FOGO);
        assertEquals(20.0, dano, DELTA);
    }

    @Test
    @DisplayName("Agua perde pela metade contra Planta")
    void aguaPerdePraPlanta() {
        double dano = ElementosTipagem.AGUA.calculoEfetividade(ElementosTipagem.PLANTA);
        assertEquals(5.0, dano, DELTA);
    }

    @Test
    @DisplayName("Planta dobra o dano contra Agua")
    void plantaDobraDanoContraAgua() {
        double dano = ElementosTipagem.PLANTA.calculoEfetividade(ElementosTipagem.AGUA);
        assertEquals(22.0, dano, DELTA);
    }

    @Test
    @DisplayName("Planta perde pela metade contra Fogo")
    void plantaPerdePraFogo() {
        double dano = ElementosTipagem.PLANTA.calculoEfetividade(ElementosTipagem.FOGO);
        assertEquals(5.5, dano, DELTA);
    }

    @Test
    @DisplayName("Golpe critico dobra o dano recebido")
    void criticoDobraODano() {
        assertEquals(20.0, ElementosTipagem.aplicarCritico(10.0), DELTA);
    }

    @Test
    @DisplayName("Quanto mais rapido o atacante, maior a chance de critico")
    void maisRapidoMaiorChanceDeCritico() {
        Pokesal lento = new Pokesal("Lento", 50, "AtkA", "AtkB", 0, ElementosTipagem.FOGO);
        Pokesal rapido = new Pokesal("Rapido", 50, "AtkA", "AtkB", 50, ElementosTipagem.FOGO);

        assertAll(
                () -> assertEquals(10, ElementosTipagem.calcularChance(lento)),
                () -> assertEquals(20, ElementosTipagem.calcularChance(rapido))
        );
    }
}
