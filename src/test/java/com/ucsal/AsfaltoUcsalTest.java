package com.ucsal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AsfaltoUcsalTest {

    private static final double DELTA = 0.0001;

    @AfterEach
    void reset() {
        ElementosTipagem.FOGO.setDano(12.0);
        ElementosTipagem.AGUA.setDano(10.0);
        ElementosTipagem.PLANTA.setDano(11.0);
    }

    @Test
    @DisplayName("Asfalto Quente aumenta o dano do Fogo em 15% e não afeta a Água")
    void QuenteAumentaDanoFogo() {
        AsfaltoUcsal.ASFALTO_QUENTE.definirVantagens();

        assertAll(
                () -> assertEquals(13.8, ElementosTipagem.FOGO.getDano(), DELTA),
                () -> assertEquals(10.0, ElementosTipagem.AGUA.getDano(), DELTA)
        );
    }

    @Test
    @DisplayName("Piso Escorregadio aumenta o dano da Agua em 10% e não afeta o Fogo")
    void AumentaDanoAgua() {
        AsfaltoUcsal.PISO_ESCORREGADIO.definirVantagens();

        assertAll(
                () -> assertEquals(11.0, ElementosTipagem.AGUA.getDano(), DELTA),
                () -> assertEquals(12.0, ElementosTipagem.FOGO.getDano(), DELTA)
        );
    }

    @Test
    @DisplayName("Canteiro Central nao altera o dano de nenhum tipo")
    void canteiroCentralNaoAltera() {
        AsfaltoUcsal.CANTEIRO_CENTRAL.definirVantagens();

        assertAll(
                () -> assertEquals(12.0, ElementosTipagem.FOGO.getDano(), DELTA),
                () -> assertEquals(10.0, ElementosTipagem.AGUA.getDano(), DELTA)
        );
    }

    @Test
    @DisplayName("Canteiro Central recupera HP de um Pokesal do tipo Planta")
    void canteiroCentralRecuperaHp() {
        Pokesal planta = new Pokesal("Folhinha", 100, "AtkA", "AtkB", 10, ElementosTipagem.PLANTA);
        planta.setHpBatalha(50);

        AsfaltoUcsal.CANTEIRO_CENTRAL.aplicarRecuperacaoHpPlanta(planta);

        assertEquals(55.0, planta.getHpBatalha(), DELTA);
    }

    @Test
    @DisplayName("Recuperacao de HP nao passa do HP maximo do Pokesal")
    void recuperacaoNaoPassaHpMaximo() {
        Pokesal planta = new Pokesal("Folhinha", 100, "AtkA", "AtkB", 10, ElementosTipagem.PLANTA);
        planta.setHpBatalha(98);

        AsfaltoUcsal.CANTEIRO_CENTRAL.aplicarRecuperacaoHpPlanta(planta);

        assertEquals(100.0, planta.getHpBatalha(), DELTA);
    }

    @Test
    @DisplayName("Recuperacao do Canteiro Central so funciona para o tipo Planta")
    void recuperacaoFuncionaPlanta() {
        Pokesal fogo = new Pokesal("Chama", 100, "AtkA", "AtkB", 10, ElementosTipagem.FOGO);
        fogo.setHpBatalha(50);

        AsfaltoUcsal.CANTEIRO_CENTRAL.aplicarRecuperacaoHpPlanta(fogo);

        assertEquals(50.0, fogo.getHpBatalha(), DELTA);
    }

    @Test
    @DisplayName("Chamar definirVantagens duas vezes acumula o bonus em dobro")
    void bonusAcumulaSeChamarDuasVezes() {
        AsfaltoUcsal.ASFALTO_QUENTE.definirVantagens();
        AsfaltoUcsal.ASFALTO_QUENTE.definirVantagens();

        assertEquals(15.87, ElementosTipagem.FOGO.getDano(), DELTA);
    }
}
