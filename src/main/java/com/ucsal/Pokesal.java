package com.ucsal;

public class Pokesal {
    private String nome;
    private static double HP;
    private String ATK;
    private String ATK2;
    private int SPD;
    private ElementosTipagem elementosTipagem;

    public Pokesal(String nome, int HP, String ATK,String ATK2, int SPD, ElementosTipagem elementosTipagem) {
        this.nome = nome;
        this.HP = HP;
        this.ATK = ATK;
        this.ATK2 = ATK2;
        this.SPD = SPD;
        this.elementosTipagem = elementosTipagem;
    }
    public void PokesalDanoSofrido(ElementosTipagem dano){
        if (HP <= 0){
            System.out.println("Pokesal derrotado! não pode receber mais dano");
        }

        HP = HP - dano.CalculoEfetividade();
        System.out.println("O dano sofrido foi de: " + dano.CalculoEfetividade());

        System.out.println("A vida restante é de: "+ HP);

    }

    public String getATK2() {
        return ATK2;
    }

    public void setATK2(String ATK2) {
        this.ATK2 = ATK2;
    }

    public String getATK() {
        return ATK;
    }

    public void setATK(String ATK) {
        this.ATK = ATK;
    }

    public ElementosTipagem getElementosTipagem() {
        return elementosTipagem;
    }

    public void setElementosTipagem(ElementosTipagem elementosTipagem) {
        this.elementosTipagem = elementosTipagem;
    }

    public double getHP() {
        return HP;
    }

    public  void setHP(double HP) {
        Pokesal.HP = HP;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSPD() {
        return SPD;
    }

    public void setSPD(int SPD) {
        this.SPD = SPD;
    }
}
