package com.ucsal;

/**
 * Efeitos de status que podem ser aplicados a um Pokesal, com sua duração em turnos.
 */
public enum EfeitosStatus {

    /**
     * Causa dano de queimadura e reduz a velocidade.
     */
    Queimado(3),

    /**
     * Causa dano de envenenamento.
     */
    Envenenado(4),

    /**
     * Reduz a velocidade do Pokesal.
     */
    Paralizado(3);

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
    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    /**
     * Aplica o efeito ao Pokesal, respeitando as imunidades por tipo.
     *
     * @param pokesal Pokesal que receberá o efeito
     */
    public void definirEfeitos(Pokesal pokesal) {

        switch (this) {
            case Queimado:
                if (pokesal.getElementosTipagem() == ElementosTipagem.Fogo) {
                    System.out.println("Não sofreu queimadura ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.Queimado);
                    System.out.println("Pokesal " + pokesal.getNome() + " foi queimado :c");
                }
                break;
            case Paralizado:
                if (pokesal.getElementosTipagem() == ElementosTipagem.Agua) {
                    System.out.println("Não sofreu paralisia ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.Paralizado);
                    System.out.println("Pokesal " + pokesal.getNome() + " foi paralizado :c");
                    pokesal.reduzirVelocidade();
                }
                break;
            case Envenenado:
                if (pokesal.getElementosTipagem() == ElementosTipagem.Planta) {
                    System.out.println("Não sofreu envenenamento ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.Paralizado);
                    System.out.println("Pokesal " + pokesal.getNome() + " foi envenenado :c");
                }
                break;
        }
    }

}
