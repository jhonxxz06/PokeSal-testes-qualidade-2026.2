package com.ucsal;

import java.util.Scanner;

/**
 * Ponto de entrada do sistema de batalhas Pokesal no terminal.
 */
public class Main {

    /**
     * Lê os dados do treinador, monta a batalha e a executa.
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        final Scanner ler = new Scanner(System.in);

        final String[] resposta1 = new String[2];

        final Pokesal[] pokesal = {
            new Pokesal("BulbaSal", 40, "Chicote de videira",
                        "Pó venenoso", 20, ElementosTipagem.Planta),
            new Pokesal("CharSal", 45, "Brasa",
                        "Fogo Fátuo", 19, ElementosTipagem.Fogo),
            new Pokesal("SquirtSal", 35, "Water Gun",
                        "Abanar da cauda", 21, ElementosTipagem.Agua),
            new Pokesal("ChikoSal", 40, "Chicote de videira",
                        "Pó venenoso", 20, ElementosTipagem.Planta),
            new Pokesal("CyndaSal", 45, "Brasa",
                        "Fogo Fátuo", 19, ElementosTipagem.Fogo),
            new Pokesal("TotoSal", 35, "Water Gun",
                        "Abanar da cauda", 21, ElementosTipagem.Agua)
        };

        final Mochila[] mochila = {
            new Mochila("Poção", "Poção voltada para recuperação após partida", 10),
            new Mochila("Super Poção", "Poção voltada para maior recuperação de vida", 5),
            new Mochila("Poção de Fúria", "Aumenta a força em 10% " +
                    "e a velocidade em 20% por um turno", 5),
            new Mochila("Poção de Força", "Aumenta a força em 25% por um turno", 5)
        };

        final TreinadorPokesal[] rivais = {
            new TreinadorPokesal("Lucas Andrade", pokesal[2], "Alagoinhas", mochila[0]),
            new TreinadorPokesal("Lázaro Brito", pokesal[5], "Itororó", mochila[1]),
            new TreinadorPokesal("Patati", pokesal[3], "Salvador", mochila[0])
        };

        System.out.println("Bem vindo ao sistema de batalhas pokesal! ");
        System.out.println("Qual seu nome treinador(a)? ");
        resposta1[0] = ler.nextLine();

        System.out.println("Deseja qual Pokesal?");
        for (int i = 0; i < pokesal.length; i++) {
            System.out.println((i + 1) + " - " + pokesal[i].getNome() +
                    " (Tipo: " + pokesal[i].getElementosTipagem() + ")");
        }
        System.out.print("Digite o número correspondente: ");
        int opcaoPokesal = 0;
        boolean valido = false;
        while (!valido) {
            if (ler.hasNextInt()) {
                opcaoPokesal = ler.nextInt();
                if (opcaoPokesal >= 1 && opcaoPokesal <= pokesal.length) {
                    valido = true;
                } else {
                    System.out.println("Número fora do intervalo! Digite de 1 a " + pokesal.length + ": ");
                }
            } else {
                System.out.println("Digite um número válido: ");
                ler.next();
            }
        }
        ler.nextLine();

        final Pokesal pokesalEscolhido = pokesal[opcaoPokesal - 1];

        System.out.println("De qual cidade você veio?");
        resposta1[1] = ler.next();
        ler.nextLine();

        System.out.println("Qual mochila deseja inicial deseja?");

        String menuMochila = "";
        for (int i = 0; i < mochila.length; i++) {
            menuMochila += (i + 1) + " - " + mochila[i].getItem() + " " + mochila[i].getItemDescricao()
                    + " com total de " + mochila[i].getQuantidades() + " Itens\n";
        }
        System.out.println(menuMochila);
        System.out.print("Digite o número correspondente: ");

        int opcaoMochila = 0;
        boolean validoMochila = false;
        while (!validoMochila) {
            if (ler.hasNextInt()) {
                opcaoMochila = ler.nextInt();
                if (opcaoMochila >= 1 && opcaoMochila <= mochila.length) {
                    validoMochila = true;
                } else {
                    System.out.println("Número fora do intervalo! Digite de 1 a " + mochila.length + ": ");
                }
            } else {
                System.out.println("Digite um número válido: ");
                ler.next();
            }
        }
        ler.nextLine();

        final Mochila mochilaEscolhida = mochila[opcaoMochila - 1];

        final TreinadorPokesal t4 = new TreinadorPokesal(resposta1[0],
                pokesalEscolhido, resposta1[1], mochilaEscolhida);

        System.out.println("\nCom quem você deseja batalhar?");
        for (int i = 0; i < rivais.length; i++) {
            System.out.println((i + 1) + " - " + rivais[i].getNome() + " (Pokesal: " +
                    rivais[i].getPokesal().getNome() + " - Cidade: " + rivais[i].getCidade() + ")");
        }
        System.out.print("Digite o número do seu oponente: ");
        final int opcaoOponente = ler.nextInt();

        final TreinadorPokesal oponenteEscolhido = rivais[opcaoOponente - 1];


        final PokesalBatalha batalha1 = new PokesalBatalha(t4, oponenteEscolhido);
        batalha1.batalha();

    }
}
