package com.ucsal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Terrenos possíveis para a batalha, cada um com um percentual que influencia
 * o dano de um tipo de Pokesal ou a recuperação de vida do tipo Planta.
 */
public enum AsfaltoUcsal {

    /** Asfalto quente, interfere no dano do tipo Fogo. */
    AsfaltoQuente(0.15),

    /** Piso escorregadio, interfere no dano do tipo Água. */
    PisoEscorregadio(0.10),

    /** Canteiro central, recupera vida dos Pokesais do tipo Planta. */
    CanteiroCentral(0.05);

    /** Percentual aplicado pelo terreno. */
    private final double percentual;

    /**
     * Cria um terreno com o percentual informado.
     *
     * @param percentual percentual aplicado pelo terreno
     */
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
                        * AsfaltoQuente.percentual);
                break;
            case PisoEscorregadio:
                ElementosTipagem.Agua.setDano(ElementosTipagem.Agua.getDano()
                        * PisoEscorregadio.percentual);
                break;
        }
    }

    /**
     * Recupera vida do Pokesal do tipo Planta de acordo com o percentual do canteiro central.
     *
     * @param pokesal Pokesal que pode recuperar vida
     */
    public void aplicarRecuperacaoHpPlanta(Pokesal pokesal) {
        if (pokesal.getElementosTipagem() == ElementosTipagem.Planta) {
            pokesal.setHpBatalha(pokesal.getHpBatalha() +
                    (pokesal.getHpBatalha() * CanteiroCentral.percentual));
        }
    }

}
