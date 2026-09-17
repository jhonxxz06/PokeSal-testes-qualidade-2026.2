package com.ucsal;

import java.util.concurrent.ThreadLocalRandom;

public enum AsfaltoUcsal {
    AsfaltoQuente(0.15), PisoEscorregadio(0.10), CanteiroCentral(0.05);
    private final double percentual;

    AsfaltoUcsal(double percentual) {
        this.percentual = percentual;
    }

    public static AsfaltoUcsal escolherAsfalto() {
        int sorteadorTerreno = ThreadLocalRandom.current().nextInt(0, 3);

        if (sorteadorTerreno == 0) {
            return AsfaltoQuente;
        } else if (sorteadorTerreno == 1) {
            return PisoEscorregadio;
        } else {
            return CanteiroCentral;
        }

    }

    public void definirVantagens(Pokesal pokesal) {
        switch (this) {
            case AsfaltoQuente:
                ElementosTipagem.Fogo.setDano(ElementosTipagem.Fogo.getDano() * AsfaltoQuente.percentual);
                break;
            case PisoEscorregadio:
                ElementosTipagem.Agua.setDano(ElementosTipagem.Agua.getDano() * PisoEscorregadio.percentual);
                break;
        }
    }

    public void aplicarRecuperacaoHpPlanta(Pokesal pokesal) {
        if (pokesal.getElementosTipagem() == ElementosTipagem.Planta) {
            pokesal.setHP(pokesal.getHP() + (pokesal.getHP() * CanteiroCentral.percentual));
        }
    }

}
