package com.ucsal;

import java.util.Random;

public class Sorteador implements GeradorAleatorio {


    @Override
    public int realizarSorte1a10(int tetoNumeros) {
        if (tetoNumeros <= 0 || tetoNumeros >= 11) {
            throw new IllegalArgumentException("Teto de número fora do Padrão! " +
                    "abaixo de 1 ou excedendo 10!!");
        }
        final Random sortear1a10 = new Random();
        final int resultado = sortear1a10.nextInt(tetoNumeros) + 1;

        return resultado;

    }

    @Override
    public int realizarSorte1a100(int tetoNumeros) {
        if (tetoNumeros <= 0 || tetoNumeros >= 101) {
            throw new IllegalArgumentException("Teto de número fora do Padrão! " +
                    "abaixo de 1 ou excedendo 100!!");
        }
            final Random sortear1a100 = new Random();
            final int resultado = sortear1a100.nextInt(tetoNumeros) + 1;

            return resultado;

    }

    // Fazer os JUnit dessas duas, o teste de ultrapassagem para cima, para baixo e normal
}
