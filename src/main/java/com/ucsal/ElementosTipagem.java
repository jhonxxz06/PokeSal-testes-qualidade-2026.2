package com.ucsal;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Tipos (elementos) dos Pokesais, com as regras de efetividade entre eles
 * e as regras de dano crítico.
 */
public enum ElementosTipagem {

    /** Tipo Fogo: forte contra Planta e fraco contra Água. */
    Fogo(12),

    /** Tipo Água: forte contra Fogo e fraco contra Planta. */
    Agua(10),

    /** Tipo Planta: forte contra Água e fraco contra Fogo. */
    Planta(11);

    /** Multiplicador aplicado quando o tipo é forte contra o adversário. */
    private final int multiplicadorPositivo = 2;

    /** Multiplicador aplicado quando o tipo é fraco contra o adversário. */
    private final double multiplicadorNegativo = 0.5;

    /** Chance base (em porcentagem) de acertar um golpe crítico. */
    private static final int ChanceBase = 10;

    /** Multiplicador aplicado ao dano quando o golpe é crítico. */
    private static final double MultiplicadorCritico = 2.0;

    /** Dano base do tipo. */
    private double dano;

    /**
     * Cria um tipo com o dano base informado.
     *
     * @param dano dano base do tipo
     */
    ElementosTipagem(double dano) {
        this.dano = dano;
    }

    /**
     * Retorna o dano base do tipo.
     *
     * @return dano base
     */
    public double getDano() {
        return dano;
    }

    /**
     * Altera o dano base do tipo.
     *
     * @param dano novo dano base
     */
    public void setDano(double dano) {
        this.dano = dano;
    }

    /**
     * Calcula o dano deste tipo contra o tipo do adversário, aplicando a vantagem
     * ou a desvantagem de tipagem.
     *
     * @param adversario tipo do Pokesal que recebe o ataque
     * @return dano calculado (1 quando os tipos são iguais ou não há regra)
     */
    public double calculoEfetividade(ElementosTipagem adversario) {
        if (this == adversario) {
            return dano;
        }
        switch (this) {
            case Fogo:
                if (adversario == Planta) {
                    return (Fogo.dano * multiplicadorPositivo);
                } else if (adversario == Agua) {
                    return (Fogo.dano * multiplicadorNegativo);
                }
            case Agua:
                if (adversario == Fogo) {
                    return (Agua.dano * multiplicadorPositivo);
                } else if (adversario == Planta) {
                    return (Agua.dano * multiplicadorNegativo);
                }
            case Planta:
                if (adversario == Agua) {
                    return (Planta.dano * multiplicadorPositivo);
                } else if (adversario == Fogo) {
                    return (Planta.dano * multiplicadorNegativo);
                }
        }
        return dano;
    }

    /**
     * Calcula a chance de crítico do atacante. Quanto mais rápido o Pokesal,
     * maior a chance de acertar um ponto fraco.
     *
     * @param atacante Pokesal que está atacando
     * @return chance de crítico, em porcentagem
     */
    public static int calcularChance(Pokesal atacante) {
        return ChanceBase + (atacante.getSPDefeito() / 5);
    }

    /**
     * Sorteia se o golpe do atacante será crítico.
     *
     * @param atacante Pokesal que está atacando
     * @return {@code true} se o golpe for crítico
     */
    public static boolean sortearCritico(Pokesal atacante) {
        final int sorteio = ThreadLocalRandom.current().nextInt(0, 101);

        return sorteio <= calcularChance(atacante);
    }

    /**
     * Aplica o multiplicador de crítico ao dano e avisa o jogador.
     *
     * @param dano dano original do golpe
     * @return dano após o multiplicador de crítico
     */
    public static double aplicarCritico(double dano) {
        System.out.println("Acertou um golpe crÃtico!");
        return dano * MultiplicadorCritico;
    }
}