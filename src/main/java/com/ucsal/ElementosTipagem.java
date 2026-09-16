package com.ucsal;


public enum ElementosTipagem {

    Fogo(12), Agua(10), Planta(11);
    private int multiplicadorPositivo = 2;
    private double multiplicadorNegativo = 0.5;
    //private final double dano;
    private double dano;
    ElementosTipagem(double dano){
        this.dano = dano;
    }

    public double getDano() {
        return dano;
    }

    public void setDano(double dano) {
        this.dano = dano;
    }

    public double CalculoEfetividade(ElementosTipagem adversario){
        if(this == adversario){
            return 1;
        }
        switch(this) {
            case Fogo:
                if(adversario == Planta) {
                    return (Fogo.dano * multiplicadorPositivo);
                    // colocar break
                }
                else if(adversario == Agua){
                    return (Fogo.dano * multiplicadorNegativo);
                }
            case Agua:
                if(adversario == Fogo){
                    return(Agua.dano * multiplicadorPositivo);
                }
                else if(adversario == Planta){
                    return (Agua.dano * multiplicadorNegativo);
                }
            case Planta:
                if(adversario == Agua){
                    return(Planta.dano * multiplicadorPositivo);
                }
                else if(adversario == Fogo){
                    return(Planta.dano * multiplicadorNegativo);
                }
    }
    return 1.0;
}
}