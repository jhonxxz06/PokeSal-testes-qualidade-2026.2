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

    /**
     * 0 ideias de como contar os turnos de efeito
     * public void ContarTurno(EfeitosStatus efeito) {
     * efeito.setTurnos(efeito.getTurnos() - 1);
     * }
     **/

// Mecanismo de randomizar para não ser toda hora que fica queimado
// Retirar efeitos após turnos
    public void DefinirEfeitos(Pokesal pokesal) {

        switch (pokesal.getElementosTipagem()) {
            case Fogo:
                if (pokesal.getElementosTipagem() == ElementosTipagem.Fogo) {
                    System.out.println("Não sofreu queimadura ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.Queimado);


                }
            case Agua:
                if (pokesal.getElementosTipagem() == ElementosTipagem.Agua) {
                    System.out.println("Não sofreu paralisia ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.Paralizado);

                }

            case Planta:
                if (pokesal.getElementosTipagem() == ElementosTipagem.Planta) {
                    System.out.println("Não sofreu envenenamento ");
                } else {
                    pokesal.setStatusAtual(EfeitosStatus.Paralizado);

                }
        }
    }

}
