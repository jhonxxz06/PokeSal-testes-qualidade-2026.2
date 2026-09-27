package com.ucsal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PokesalTest {

    private static final double DELTA = 0.0001;

    @Test
    @DisplayName("HP de batalha comeca igual ao HP maximo")
    void hpBatalhaHpMaximo() {
        Pokesal p = new Pokesal("Teste", 80, "AtkA", "AtkB", 10, ElementosTipagem.FOGO);
        assertEquals(80.0, p.getHpBatalha(), DELTA);
    }

    @Test
    @DisplayName("Criar um Pokesal com HP zero nao gera erro")
     void hpZero() {
        Pokesal p = new Pokesal("SemVida", 0, "AtkA", "AtkB", 10, ElementosTipagem.FOGO);
        assertEquals(0.0, p.getHpBatalha(), DELTA);
    }

    @Test
    @DisplayName("Pokesal ja derrotado nao recebe mais dano")
    void danoPosDerrota() {
        Pokesal atacante = new Pokesal("Atacante", 50, "AtkA", "AtkB", 10, ElementosTipagem.FOGO);
        Pokesal derrotado = new Pokesal("Derrotado", 50, "AtkA", "AtkB", 10, ElementosTipagem.AGUA);
        derrotado.setHpBatalha(0);

        derrotado.pokesalDanoSofrido(atacante);

        assertEquals(0.0, derrotado.getHpBatalha(), DELTA);
    }

    @Test
    @DisplayName("Dano sofrido desconta corretamente da vida do defensor")
    void danoSofridoDescontaDaVida() {
        Pokesal atacante = new Pokesal("Atacante", 50, "AtkA", "AtkB", 10, ElementosTipagem.FOGO);
        Pokesal defensor = new Pokesal("Defensor", 50, "AtkA", "AtkB", 10, ElementosTipagem.FOGO);

        defensor.pokesalDanoSofrido(atacante);

        double semCritico = 50.0 - 12.0;
        double comCritico = 50.0 - 24.0;
        boolean ok = Math.abs(defensor.getHpBatalha() - semCritico) < DELTA
                || Math.abs(defensor.getHpBatalha() - comCritico) < DELTA;

        assertTrue(ok, "esperava 38.0 (normal) ou 26.0 (crítico), veio " + defensor.getHpBatalha());
    }

    @Test
    @DisplayName("Pocao de Furia aumenta forca em 10% e velocidade em 20%")
    void pocaoFuria() {
        Pokesal p = new Pokesal("Furioso", 50, "AtkA", "AtkB", 100, ElementosTipagem.FOGO);

        p.aplicarPocaoFuria();

        assertAll(
                () -> assertEquals(1.10, p.getForcaEfeito(), DELTA),
                () -> assertEquals(120, p.getSPDefeito())
        );
    }

    @Test
    @DisplayName("Efeito da Pocao de Furia termina apos dois contarTurnoPocao")
    void tempoEfeitoFuria() {
        Pokesal p = new Pokesal("Furioso", 50, "AtkA", "AtkB", 100, ElementosTipagem.FOGO);
        p.aplicarPocaoFuria();

        p.contarTurnoPocao();
        assertEquals(1.10, p.getForcaEfeito(), DELTA);

        p.contarTurnoPocao();
        assertAll(
                () -> assertEquals(1.0, p.getForcaEfeito(), DELTA),
                () -> assertEquals(100, p.getSPDefeito())
        );
    }

    @Test
    @DisplayName("Pocao de Forca aumenta forca em 25% e nao altera velocidade")
    void pocaoForcaAumenta() {
        Pokesal p = new Pokesal("Forcudo", 50, "AtkA", "AtkB", 100, ElementosTipagem.FOGO);

        p.aplicarPocaoForca();

        assertAll(
                () -> assertEquals(1.25, p.getForcaEfeito(), DELTA),
                () -> assertEquals(100, p.getSPDefeito())
        );
    }

    @Test
    @DisplayName("Efeito da Pocao de Forca termina apos dois contarTurnoPocao")
    void tempoEfeitoDaForca() {
        Pokesal p = new Pokesal("Forcudo", 50, "AtkA", "AtkB", 100, ElementosTipagem.FOGO);
        p.aplicarPocaoForca();

        p.contarTurnoPocao();
        assertEquals(1.25, p.getForcaEfeito(), DELTA);

        p.contarTurnoPocao();
        assertEquals(1.0, p.getForcaEfeito(), DELTA);
    }
}
