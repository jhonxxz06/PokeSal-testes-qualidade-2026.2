package com.ucsal;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PokesalBatalha {
    Scanner ler = new Scanner(System.in);
    private final TreinadorPokesal desafiante1;
    private final TreinadorPokesal desafiante2;
    private int contadorTurnos = 0;
    private final AsfaltoUcsal tipoAsfalto;

    private static final int ChanceEfeitoATK1 = 10;
    private static final int ChanceEfeitoATK2 = 75;
    public PokesalBatalha(TreinadorPokesal desafiante1, TreinadorPokesal desafiante2) {
        this.desafiante1 = desafiante1;
        this.desafiante2 = desafiante2;
        this.tipoAsfalto = AsfaltoUcsal.escolherAsfalto();
    }

    public void Batalha() {
        tipoAsfalto.definirVantagens(desafiante1.getPokesal());
        tipoAsfalto.definirVantagens(desafiante2.getPokesal());
        int opSelecionada;

        if (desafiante1.getPokesal().getSPDefeito() > desafiante2.getPokesal().getSPDefeito()) {

            while (desafiante1.getPokesal().getHpBatalha() > 0 && desafiante2.getPokesal().getHpBatalha() > 0) {
                if (desafiante1.getPokesal().getElementosTipagem() == ElementosTipagem.Planta) {
                    tipoAsfalto.aplicarRecuperacaoHpPlanta(desafiante1.getPokesal());
                }
                if (desafiante2.getPokesal().getElementosTipagem() == ElementosTipagem.Planta) {
                    tipoAsfalto.aplicarRecuperacaoHpPlanta(desafiante2.getPokesal());
                }
                System.out.println("Pokesal - Temos que formá-los!");

                if (contadorTurnos % 2 == 0) {
                    exibirMenu(desafiante1);
                    opSelecionada = ler.nextInt();
                    executarEscolha(opSelecionada, desafiante1, desafiante2);
                    desafiante1.getPokesal().contarTurnoEfeito();
                    contadorTurnos += 1;
                } else {
                    exibirMenu(desafiante2);
                    opSelecionada = ler.nextInt();
                    executarEscolha(opSelecionada,desafiante2,desafiante1);
                    desafiante2.getPokesal().contarTurnoEfeito();
                    contadorTurnos += 1;
                }
            }
            if (desafiante1.getPokesal().getHpBatalha() <= 0) {
                System.out.println("O oponente " + desafiante2.getNome() + " do Pokesal " + desafiante2.getPokesal().getNome() + " foi o vencedor");
            }
            else {
                System.out.println("O oponente " + desafiante1.getNome() + " do Pokesal " + desafiante1.getPokesal().getNome() + " foi o vencedor");
            }
        }
        else {
            while (desafiante2.getPokesal().getHpBatalha() > 0 && desafiante1.getPokesal().getHpBatalha() > 0) {
                if (desafiante1.getPokesal().getElementosTipagem() == ElementosTipagem.Planta) {
                    tipoAsfalto.aplicarRecuperacaoHpPlanta(desafiante1.getPokesal());
                }
                if (desafiante2.getPokesal().getElementosTipagem() == ElementosTipagem.Planta) {
                    tipoAsfalto.aplicarRecuperacaoHpPlanta(desafiante2.getPokesal());
                }
                System.out.println("Pokesal - Temos que formá-los!");
                if (contadorTurnos % 2 == 0) {
                    exibirMenu(desafiante2);
                    opSelecionada = ler.nextInt();
                    executarEscolha(opSelecionada,desafiante2,desafiante1);
                    desafiante2.getPokesal().contarTurnoEfeito();
                    contadorTurnos += 1;
                }
                else {
                    exibirMenu(desafiante1);
                    opSelecionada = ler.nextInt();
                    executarEscolha(opSelecionada,desafiante1,desafiante2);
                    desafiante1.getPokesal().contarTurnoEfeito();
                    contadorTurnos += 1;
                }
            }

            if (desafiante1.getPokesal().getHpBatalha() <= 0) {
                System.out.println("O oponente " + desafiante2.getNome() + " do Pokesal " + desafiante2.getPokesal().getNome() + " foi o vencedor");
            } else {
                System.out.println("O oponente " + desafiante1.getNome() + " do Pokesal " + desafiante1.getPokesal().getNome() + " foi o vencedor");

            }
        }

    }
    private void exibirMenu(TreinadorPokesal atacante) {
        System.out.println("Selecione suas opções Treinador(a) " + atacante.getNome());
        System.out.println("1 - Atacar Oponente " +
                "\n 2 - Aplicar Efeito Oponente" +
                "\n 3 - Usar Poção " + atacante.getItensUso() + "/2 " +
                "\n 4 - Fugir da batalha");
    }

    private void executarEscolha(int opcao, TreinadorPokesal atacante, TreinadorPokesal defensor){
        switch (opcao) {
            case 1:
                defensor.getPokesal().PokesalDanoSofrido(atacante.getPokesal().getElementosTipagem());
                SorteioEfeito(atacante,defensor,ChanceEfeitoATK1);
                break;
            case 2:
                SorteioEfeito(atacante,defensor,ChanceEfeitoATK2);
            case 3:
                atacante.getAcessorios().usarPocao(atacante, atacante.getAcessorios());
                break;
            case 4:
                System.out.println(atacante.getNome() + " fugiu da batalha..." + defensor.getNome() + " venceu...");
                atacante.getPokesal().setHpBatalha(0);
                break;
        }
    }

    private EfeitosStatus definirEfeitoTipo(ElementosTipagem atacante){
        switch(atacante){
            case Fogo:
                return EfeitosStatus.Queimado;
            case Agua:
                return EfeitosStatus.Paralizado;
            case Planta:
                return EfeitosStatus.Envenenado;
            default:
                return null;
        }
    }

    private void SorteioEfeito(TreinadorPokesal atacante, TreinadorPokesal defensor, int chanceAcontecer){
        EfeitosStatus efeito = definirEfeitoTipo(atacante.getPokesal().getElementosTipagem());

        if(defensor.getPokesal().getStatusAtual() != null){
            return;
        }
        int sorteio = ThreadLocalRandom.current().nextInt(0, 101);

        if(sorteio <= chanceAcontecer){
            efeito.DefinirEfeitos(defensor.getPokesal());
        }
    }


}
