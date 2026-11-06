package com.ucsal;

import java.util.logging.Level;

import java.util.logging.Logger;
/**
 * Efeitos de status que podem ser aplicados a um Pokesal, com sua duração em turnos.
 */

public enum EfeitosStatus {
    /**
     * Causa dano de queimadura e reduz a velocidade.
     */
     QUEIMADO(3),

    /**
     * Causa dano de envenenamento.
     */
     ENVENENADO(4),

    /**
     * Reduz a velocidade do Pokesal.
     */
     PARALIZADO(3);

    Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Quantidade de turnos em que o efeito permanece.
     */
    private int turnos;

    /**
     * Cria um efeito com a duração informada.
     *
     * @param turnos quantidade de turnos do efeito
     */
    EfeitosStatus(int turnos) {
        this.turnos = turnos;
    }

    /**
     * Retorna a duração do efeito.
     *
     * @return quantidade de turnos do efeito
     */
    public int getTurnos() {
        return turnos;
    }

    /**
     * Altera a duração do efeito.
     *
     * @param turnos nova quantidade de turnos do efeito
     */
    void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    /**
     * Aplica o efeito ao Pokesal, respeitando as imunidades por tipo.
     *
     * @param pokesal Pokesal que receberá o efeito
     */
    public void definirEfeitos(Pokesal pokesal) {

        switch (this) {
            case QUEIMADO:
                if (pokesal.getElementosTipagem() == ElementosTipagem.FOGO) {
                    logger.info("Não sofreu queimadura ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.QUEIMADO);
                    logger.log(Level.INFO, () -> retornarNome(pokesal) + " foi queimado :c ");
                }
                break;
            case PARALIZADO:
                if (pokesal.getElementosTipagem() == ElementosTipagem.AGUA) {
                    logger.info("Não sofreu paralisia ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.PARALIZADO);
                    logger.log(Level.INFO, () -> retornarNome(pokesal) + " foi paralizado :c");
                    pokesal.reduzirVelocidade();
                }
                break;
            case ENVENENADO:
                if (pokesal.getElementosTipagem() == ElementosTipagem.PLANTA) {
                    logger.info("Não sofreu envenenamento ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.ENVENENADO);
                    logger.log(Level.INFO, () -> retornarNome(pokesal) + " foi envenenado :c");
                }
                break;
        }
    }

    private static String retornarNome(Pokesal pokesal) {
        return "Pokesal " + pokesal.getNome();
    }

}
