package com.ucsal;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

/**
 * Terrenos possíveis para a batalha, cada um com um percentual que influencia
 * o dano de um tipo de Pokesal ou a recuperação de vida do tipo Planta.
 */
public enum AsfaltoUcsal {

    ASFALTO_QUENTE(0.15),  PISO_ESCORREGADIO(0.10),  CANTEIRO_CENTRAL(0.05);
    private final double percentual;

    Logger logger = Logger.getLogger(getClass().getName());

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
            return ASFALTO_QUENTE;
        } else if (sorteadorTerreno == 1) {
            return PISO_ESCORREGADIO;
        } else {
            return CANTEIRO_CENTRAL;
        }

    }

    /**
     * Aplica ao dano do tipo afetado o percentual deste terreno.
     *
     * @param pokesal Pokesal que participa da batalha
     */
    public void definirVantagens(Pokesal pokesal) {
        switch (this) {
            case ASFALTO_QUENTE:
                ElementosTipagem.FOGO.setDano(ElementosTipagem.FOGO.getDano()
                        * (1 + ASFALTO_QUENTE.percentual));
                break;
            case PISO_ESCORREGADIO:
                ElementosTipagem.AGUA.setDano(ElementosTipagem.AGUA.getDano()
                        * (1 + PISO_ESCORREGADIO.percentual));
                break;
            default:
                logger.warning(" Erro! Terreno fora do escopo!");
                break;
        }
    }

    /**
     * Recupera vida do Pokesal do tipo Planta de acordo com o percentual do canteiro central.
     *
     * @param pokesal Pokesal que pode recuperar vida
     */
    public void aplicarRecuperacaoHpPlanta(Pokesal pokesal) {
        if (this == CANTEIRO_CENTRAL
                && pokesal.getElementosTipagem() == ElementosTipagem.PLANTA) {
            final double hpRecuperado = pokesal.getHpBatalha()
                    + (pokesal.getHP() * CANTEIRO_CENTRAL.percentual);
            pokesal.setHpBatalha(Math.min(hpRecuperado, pokesal.getHP()));
        }
    }

}