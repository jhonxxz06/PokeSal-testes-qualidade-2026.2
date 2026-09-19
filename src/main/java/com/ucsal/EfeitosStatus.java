package com.ucsal;

public enum EfeitosStatus {
    Queimado(3), Envenenado(4), Paralizado(3);
    private int turnos;

    EfeitosStatus(int turnos) {
        this.turnos = turnos;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public void DefinirEfeitos(Pokesal pokesal) {

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
