package com.ucsal;

import java.util.concurrent.ThreadLocalRandom;

public class DanoCritico {
  private static final int ChanceBase = 10;

  private static final double MultiplicadorCritico = 2.0;

  // quanto mais rapido o pokesal, maior a chance de acertar um ponto fraco
  public static int calcularChance(Pokesal atacante) {
    return ChanceBase + (atacante.getSPDefeito() / 5);
  }

  public static boolean sortearCritico(Pokesal atacante) {
    final int sorteio = ThreadLocalRandom.current().nextInt(0, 101);

    return sorteio <= calcularChance(atacante);
  }

  public static double aplicarCritico(double dano) {
    System.out.println("Acertou um golpe crítico!");
    return dano * MultiplicadorCritico;
  }
}