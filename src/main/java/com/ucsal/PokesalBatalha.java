package com.ucsal;
import java.util.Scanner;
public class PokesalBatalha {
    private TreinadorPokesal desafiante1;
    private TreinadorPokesal desafiante2;
    private int contadorTurnos = 0;
    private AsfaltoUcsal tipoAsfalto;
// criar um método não um construtor talvez

    Scanner ler = new Scanner(System.in);
    public void BatalhaPokesal(TreinadorPokesal desafiante1, TreinadorPokesal desafiante2) {
        int opSelecionada;
        if (desafiante1.getPokesal().getSPD() > desafiante2.getPokesal().getSPD()) {
            while (desafiante1.getPokesal().getHP() > 0 && desafiante2.getPokesal().getHP() > 0) {

                System.out.println("Pokesal - Temos que forma-los!");

                if (contadorTurnos % 2 == 0) {

                    System.out.println("Selecione suas opções Treinador(a) " + desafiante1.getNome());

                    System.out.println("1 - Atacar Oponente " +
                            "\n 2 - Usar Poção " + desafiante1.getItensUso() + "/2 " +
                            "\n 3 - Fugir da batalha");

                    opSelecionada = ler.nextInt();
                    switch (opSelecionada) {
                        case 1:
                            desafiante2.getPokesal().PokesalDanoSofrido(desafiante1.getPokesal().getElementosTipagem().CalculoEfetividade(desafiante2.getPokesal().getElementosTipagem());
                        case 2:
                            desafiante1.getAcessorios().usarPocao(desafiante1,desafiante1.getAcessorios());
                    }
                    contadorTurnos += 1;
                }
                else {

                    System.out.println("Selecione suas opções Treinador(a) " + desafiante2.getNome());

                    System.out.println("1 - Atacar Oponente " +
                            "\n 2 - Usar Poção " + desafiante2.getItensUso() + "/2 " +
                            "\n 3 - Fugir da batalha");

                    opSelecionada = ler.nextInt();
                    switch (opSelecionada) {
                        case 1:

                        case 2:
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
            while (desafiante2.getPokesal().getHP() > 0 && desafiante1.getPokesal().getHP() > 0) {
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
