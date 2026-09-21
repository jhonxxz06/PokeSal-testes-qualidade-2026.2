package com.ucsal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Terrenos possíveis para a batalha, cada um com um percentual que influencia
 * o dano de um tipo de Pokesal ou a recuperação de vida do tipo Planta.
 */
public enum AsfaltoUcsal {
    AsfaltoQuente(0.15), PisoEscorregadio(0.10), CanteiroCentral(0.05);
    private final double percentual;

    AsfaltoUcsal(double percentual) {
        this.percentual = percentual;
    }

    /**
     * Sorteia o terreno da batalha, com a mesma chance para cada terreno.
     *
     * @return terreno sorteado
     */
    public static AsfaltoUcsal escolherAsfalto() {
        final int sorteadorTerreno = ThreadLocalRandom.current().nextInt(0, 3);

        if (sorteadorTerreno == 0) {
            return AsfaltoQuente;
        } else if (sorteadorTerreno == 1) {
            return PisoEscorregadio;
        } else {
            return CanteiroCentral;
        }

    }

    /**
     * Aplica ao dano do tipo afetado o percentual deste terreno.
     *
     * @param pokesal Pokesal que participa da batalha
     */
    public void definirVantagens(Pokesal pokesal) {
        switch (this) {
            case AsfaltoQuente:
                ElementosTipagem.Fogo.setDano(ElementosTipagem.Fogo.getDano()
                        * (1 + AsfaltoQuente.percentual));
                break;
            case PisoEscorregadio:
                ElementosTipagem.Agua.setDano(ElementosTipagem.Agua.getDano()
                        * (1 + PisoEscorregadio.percentual));
                break;
        }
    }

    /**
     * Recupera vida do Pokesal do tipo Planta de acordo com o percentual do canteiro central.
     *
     * @param pokesal Pokesal que pode recuperar vida
     */
    public void aplicarRecuperacaoHpPlanta(Pokesal pokesal) {
        if (this == CanteiroCentral
                && pokesal.getElementosTipagem() == ElementosTipagem.Planta) {
            final double hpRecuperado = pokesal.getHpBatalha()
                    + (pokesal.getHP() * CanteiroCentral.percentual);
            pokesal.setHpBatalha(Math.min(hpRecuperado, pokesal.getHP()));
        }
    }

}