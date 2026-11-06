package com.ucsal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PokesalBatalhaTest {

    private final InputStream systemInOriginal = System.in;

    private TreinadorPokesal rapido;
    private TreinadorPokesal lento;

    @BeforeEach
    void criarTreinadores() {
        rapido = criarTreinador("Rapido", 100, "Poção Comum");
        lento = criarTreinador("Lento", 10, "Poção Comum");
    }

    @AfterEach
    void restaurar() {
        System.setIn(systemInOriginal);
        ElementosTipagem.FOGO.setDano(12.0);
        ElementosTipagem.AGUA.setDano(10.0);
        ElementosTipagem.PLANTA.setDano(11.0);
    }

    private TreinadorPokesal criarTreinador(String nome, int spd, String pocaoNome) {
        Pokesal pokesal = new Pokesal(nome + "Pokesal", 50, "AtkA", "AtkB", spd, ElementosTipagem.FOGO);
        Mochila mochila = new Mochila(pocaoNome, "poção de teste", 5);
        return new TreinadorPokesal(nome, pokesal, "Salvador", mochila);
    }

    private void simularTresTentativas(TreinadorPokesal atacante, TreinadorPokesal outro) {

        System.setIn(new ByteArrayInputStream("3\n9\n3\n9\n3\n9\n4\n".getBytes()));

        PokesalBatalha batalha = new PokesalBatalha(atacante, outro);
        batalha.batalha();
    }

    @Test
    @DisplayName("Quem tem maior SPD ataca primeiro e assim decide o resultado")
    void quemTemMaiorSpdAtacaPrimei() {
        System.setIn(new ByteArrayInputStream("4\n".getBytes()));

        PokesalBatalha batalha = new PokesalBatalha(rapido, lento);
        batalha.batalha();

        assertAll(
                () -> assertEquals(0.0, rapido.getPokesal().getHpBatalha(), 0.0001),
                () -> assertEquals(50.0, lento.getPokesal().getHpBatalha(), 0.0001)
        );
    }

    @Test
    @DisplayName("Poção Comum: 3ª tentativa de uso não é aplicada, itensUso trava em 2")
    void limitaPocaoComum() {
        simularTresTentativas(rapido, lento);

        assertAll(
                () -> assertEquals(2, rapido.getItensUso()),
                () -> assertEquals(3, rapido.getAcessorios().getQuantidades())
        );
    }

    @Test
    @DisplayName("Poção de Fúria: 3ª tentativa de uso não é aplicada, itensUso trava em 2")
    void limitaPocaoFuria() {
        TreinadorPokesal comFuria = criarTreinador("Rapido", 100, "Poção de Fúria");

        simularTresTentativas(comFuria, lento);

        assertAll(
                () -> assertEquals(2, comFuria.getItensUso()),
                () -> assertEquals(3, comFuria.getAcessorios().getQuantidades())
        );
    }

    @Test
    @DisplayName("Poção de Força: 3ª tentativa de uso não é aplicada, itensUso trava em 2")
    void limitaPocaoForca() {
        TreinadorPokesal comForca = criarTreinador("Rapido", 100, "Poção de Força");

        simularTresTentativas(comForca, lento);

        assertAll(
                () -> assertEquals(2, comForca.getItensUso()),
                () -> assertEquals(3, comForca.getAcessorios().getQuantidades())
        );
    }
}
