package com.ucsal;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Controla uma batalha entre dois treinadores, turno a turno, no terminal.
 */
public class PokesalBatalha {

    /**
     * Chance (em porcentagem) do efeito de status após um ataque.
     */
    private static final int ChanceEfeitoATK1 = 10;
    /**
     * Chance (em porcentagem) do efeito de status ao escolher aplicar efeito.
     */

    private static final int ChanceEfeitoATK2 = 75;
    /**
     * Primeiro treinador da batalha.
     */

    private final TreinadorPokesal desafiante1;
    /**
     * Segundo treinador da batalha.
     */

    private final TreinadorPokesal desafiante2;
    /**
     * Terreno sorteado para a batalha.
     */

    private final AsfaltoUcsal tipoAsfalto;
    /**
     * Leitor da entrada do terminal.
     */

    Scanner ler = new Scanner(System.in);
    /**
     * Quantidade de turnos já jogados.
     */

    private int contadorTurnos = 0;

    /**
     * Cria uma batalha e sorteia o terreno.
     *
     * @param desafiante1 primeiro treinador
     * @param desafiante2 segundo treinador
     */
    public PokesalBatalha(TreinadorPokesal desafiante1, TreinadorPokesal desafiante2) {
        this.desafiante1 = desafiante1;
        this.desafiante2 = desafiante2;
        this.tipoAsfalto = AsfaltoUcsal.escolherAsfalto();
    }

    /**
     * Executa a batalha até um dos Pokesais ficar sem vida. Começa o Pokesal mais rápido
     * e, ao final, exibe o vencedor.
     */
    public void batalha() {
        tipoAsfalto.definirVantagens(desafiante1.getPokesal());
        tipoAsfalto.definirVantagens(desafiante2.getPokesal());
        System.out.println("O terreno da batalha é " + tipoAsfalto);

        TreinadorPokesal primeiro = desafiante1.getPokesal().getSPDefeito() > desafiante2.getPokesal().getSPDefeito()
                ? desafiante1 : desafiante2;
        TreinadorPokesal segundo = (primeiro == desafiante1) ? desafiante2 : desafiante1;

        executarBatalha(primeiro, segundo);
    }

    private void executarBatalha(TreinadorPokesal primeiro, TreinadorPokesal segundo) {
        int opSelecionada;
        while (primeiro.getPokesal().getHpBatalha() > 0 && segundo.getPokesal().getHpBatalha() > 0) {
            if (primeiro.getPokesal().getElementosTipagem() == ElementosTipagem.Planta) {
                tipoAsfalto.aplicarRecuperacaoHpPlanta(primeiro.getPokesal());
            }
            if (segundo.getPokesal().getElementosTipagem() == ElementosTipagem.Planta) {
                tipoAsfalto.aplicarRecuperacaoHpPlanta(segundo.getPokesal());
            }
            System.out.println("Pokesal - Temos que formá-los!");

            TreinadorPokesal atacanteDaVez = (contadorTurnos % 2 == 0) ? primeiro : segundo;
            TreinadorPokesal defensorDaVez  = (contadorTurnos % 2 == 0) ? segundo : primeiro;

            exibirMenu(atacanteDaVez);
            opSelecionada = ler.nextInt();
            executarEscolha(opSelecionada, atacanteDaVez, defensorDaVez);
            atacanteDaVez.getPokesal().contarTurnoEfeito();
            atacanteDaVez.getPokesal().contarTurnoPocao();
            contadorTurnos += 1;
        }

        TreinadorPokesal vencedor = primeiro.getPokesal().getHpBatalha() <= 0 ? segundo : primeiro;
        System.out.println("O oponente " + vencedor.getNome() +
                " do Pokesal " + vencedor.getPokesal().getNome() + " foi o vencedor");
    }

    /**
     * Exibe o menu principal do turno.
     *
     * @param atacante treinador que joga o turno
     */
    private void exibirMenu(TreinadorPokesal atacante) {
        System.out.println("Selecione suas opções Treinador(a) " + atacante.getNome());
        System.out.println("1 - Atacar Oponente " +
                "\n2 - Aplicar Efeito Oponente" +
                "\n3 - Usar Poção " + atacante.getItensUso() + "/2 " +
                "\n4 - Fugir da batalha");
    }

    /**
     * Executa a opção escolhida no menu principal.
     *
     * @param opcao    opção escolhida
     * @param atacante treinador que joga o turno
     * @param defensor treinador que recebe a ação
     */
    private void executarEscolha(int opcao, TreinadorPokesal atacante, TreinadorPokesal defensor) {
        switch (opcao) {
            case 1:
                defensor.getPokesal().pokesalDanoSofrido(atacante.getPokesal());
                sorteioEfeito(atacante, defensor, ChanceEfeitoATK1);
                break;
            case 2:
                sorteioEfeito(atacante, defensor, ChanceEfeitoATK2);
                break;

            case 3:
                if (atacante.getItensUso()>=2){
                    System.out.println("Limite de poções por batalha atingido!");
                    break;
                }
                usarPocao(atacante);
                break;
            case 4:
                System.out.println(atacante.getNome() + " fugiu da batalha..."
                        + defensor.getNome() + " venceu...");
                atacante.getPokesal().setHpBatalha(0);
                break;
            default:
                System.out.println("Opção inválida, turno perdido!");
        }
    }

    /**
     * Usa o item da mochila do treinador de acordo com o nome do item
     * (poção de fúria, poção de força, super poção ou poção comum).
     *
     * @param atacante treinador que está usando o item
     */
    private void usarPocao(TreinadorPokesal atacante) {
        final Mochila pocao = atacante.getAcessorios();

        if (pocao.getItem().equals("Poção de Fúria")) {
            pocao.usarPocaoFuria(atacante, pocao);
        } else if (pocao.getItem().equals("Poção de Força")) {
            pocao.usarPocaoForca(atacante, pocao);
        } else if (pocao.getItem().equals("Super Poção")) {
            pocao.usarSuperPocao(atacante, pocao);
        } else {
            pocao.usarPocao(atacante, pocao);
        }
    }

    /**
     * Define o efeito de status que o tipo do atacante aplica.
     *
     * @param atacante tipo do Pokesal atacante
     * @return efeito de status do tipo, ou {@code null} se não houver
     */
    private EfeitosStatus definirEfeitoTipo(ElementosTipagem atacante) {
        switch (atacante) {
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

    /**
     * Sorteia se o efeito de status será aplicado ao defensor. Nada acontece se
     * o defensor já estiver com algum efeito.
     *
     * @param atacante        treinador que aplica o efeito
     * @param defensor        treinador que pode receber o efeito
     * @param chanceAcontecer chance (em porcentagem) de o efeito acontecer
     */
    private void sorteioEfeito(TreinadorPokesal atacante,
                               TreinadorPokesal defensor, int chanceAcontecer) {
        final EfeitosStatus efeito = definirEfeitoTipo(atacante.getPokesal().getElementosTipagem());

        if (defensor.getPokesal().getStatusAtual() != null) {
            return;
        }
        final int sorteio = ThreadLocalRandom.current().nextInt(0, 101);

        if (sorteio <= chanceAcontecer) {
            efeito.definirEfeitos(defensor.getPokesal());
        }
    }


}
