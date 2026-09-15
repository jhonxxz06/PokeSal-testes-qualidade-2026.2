package com.ucsal;

public class PokesalBatalha {
    private TreinadorPokesal desafiante1;
    private TreinadorPokesal desafiante2;
    private int contadorTurnos = 0;

    public void BatalhaPokesal(TreinadorPokesal desafiante1, TreinadorPokesal desafiante2) {
        if (desafiante1.getPokesal().getSPD() > desafiante2.getPokesal().getSPD()) {
            while (desafiante1.getPokesal().getHP() > 0 || desafiante2.getPokesal().getHP() > 0) {
                System.out.println("Pokesal - Temos que forma-los!");
                if (contadorTurnos % 2 == 0) {
                    System.out.println("Selecione suas opções Treinador(a) " + desafiante1.getNome());
                    System.out.println("1 - Atacar Oponente " +
                            "\n 2 - Usar Poção " + desafiante1.getItensUso() + "/2 " +
                            "\n 3 - Fugir da batalha");
                    switch (12) {
                    }
                    contadorTurnos += 1;
                }
                else {
                    System.out.println("Selecione suas opções Treinador(a) " + desafiante2.getNome());
                    System.out.println("1 - Atacar Oponente " +
                            "\n 2 - Usar Poção " + desafiante2.getItensUso() + "/2 " +
                            "\n 3 - Fugir da batalha");
                    switch (12) {
                    }
                    contadorTurnos += 1;
                }
            }
            if(desafiante1.getPokesal().getHP() <= 0){
                System.out.println("O oponente " + desafiante2.getNome() + "do Pokesal " + desafiante2.getPokesal().getNome() + "Foi o vencedor");
            }
            else{
                System.out.println("O oponente " + desafiante1.getNome() + "do Pokesal " + desafiante1.getPokesal().getNome() + "Foi o vencedor");
            }
        }
        else{
            while (desafiante2.getPokesal().getHP() > 0 || desafiante1.getPokesal().getHP() > 0) {
                System.out.println("Pokesal - Temos que forma-los!");
                if (contadorTurnos % 2 == 0) {
                    System.out.println("Selecione suas opções Treinador(a) " + desafiante2.getNome());
                    System.out.println("1 - Atacar Oponente " +
                            "\n 2 - Usar Poção " + desafiante2.getItensUso() + "/2 " +
                            "\n 3 - Fugir da batalha");
                    switch (12) {
                    }
                    contadorTurnos += 1;
                }
                else {
                    System.out.println("Selecione suas opções Treinador(a) " + desafiante1.getNome());
                    System.out.println("1 - Atacar Oponente " +
                            "\n 2 - Usar Poção " + desafiante1.getItensUso() + "/2 " +
                            "\n 3 - Fugir da batalha");
                    switch (12) {
                    }
                    contadorTurnos += 1;
                }
            }
            if(desafiante1.getPokesal().getHP() <= 0){
                System.out.println("O oponente " + desafiante2.getNome() + "do Pokesal " + desafiante2.getPokesal().getNome() + "Foi o vencedor");
            }
            else{
                System.out.println("O oponente " + desafiante1.getNome() + "do Pokesal " + desafiante1.getPokesal().getNome() + "Foi o vencedor");

            }
        }

    }

}
