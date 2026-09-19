package com.ucsal;

public class Pokesal {
    private double HP;
    private double HpBatalha = HP;
    private String nome;
    private String ATK;
    private String ATK2;
    private int SPD;
    private int SPDefeito = SPD;
    private ElementosTipagem elementosTipagem;
    private EfeitosStatus statusAtual = null;
    private int turnosSofridos = 0;

    public Pokesal(String nome, double HP, String ATK, String ATK2, int SPD, ElementosTipagem elementosTipagem) {
        this.nome = nome;
        this.HP = HP;
        this.HpBatalha = HP;
        this.ATK = ATK;
        this.ATK2 = ATK2;
        this.SPD = SPD;
        this.SPDefeito = SPD;
        this.elementosTipagem = elementosTipagem;
    }

    public void PokesalDanoSofrido(ElementosTipagem dano) {
        if (HP <= 0) {
            System.out.println("Pokesal derrotado! não pode receber mais dano");
        }

        double danototal = dano.CalculoEfetividade(this.elementosTipagem);
        HpBatalha = HpBatalha - danototal;
        System.out.println("O dano sofrido foi de: " + danototal);

        System.out.println("A vida restante é de: " + HpBatalha);

    }

    public void contarTurnoEfeito(){
        if(getStatusAtual() != null){
            if(turnosSofridos < statusAtual.getTurnos()) {
                turnosSofridos += 1;
                // fazer a diferença de sofrer dano ou a redução de velocidade fixa (acho que não é necessário)
                System.out.println("Pokesal " + getNome() + " sofreu os efeitos");
                sofrerDanoEfeitos();
            }
            else{
                if(statusAtual == EfeitosStatus.Paralizado){
                    SPDefeito = SPD;
                }
                System.out.println("Pokesal " + getNome() + " se livrou dos efeito!");
                statusAtual = null;
                turnosSofridos = 0;
            }
        }
    }

    public void sofrerDanoEfeitos(){
        if(statusAtual == EfeitosStatus.Queimado){
            System.out.println(getNome() + " sofreu com queimadura!E teve sua velocidade reduzida...");
            HpBatalha = HpBatalha - (getHP() * 0.1);
        }
        else if(statusAtual == EfeitosStatus.Envenenado){
            System.out.println(getNome() + " sofreu com envenenamento");
            HpBatalha = HpBatalha - (getHP() * 0.2);
        }
    }
    public void reduzirVelocidade(){
        if(statusAtual == EfeitosStatus.Paralizado){
            System.out.println(getNome() + " sofreu com paralisia! Velocidade reduzida...");
            SPDefeito = getSPD() - 10;
        }
        if(statusAtual == EfeitosStatus.Queimado){
            SPDefeito = getSPD() - 5;
        }
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

    public void setHP(double HP) {
        this.HP = HP;
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

    public EfeitosStatus getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(EfeitosStatus statusAtual) {
        this.statusAtual = statusAtual;
    }

    public double getHpBatalha() {
        return HpBatalha;
    }

    public void setHpBatalha(double hpBatalha) {
        HpBatalha = hpBatalha;
    }

    public int getTurnosSofridos() {
        return turnosSofridos;
    }
}
