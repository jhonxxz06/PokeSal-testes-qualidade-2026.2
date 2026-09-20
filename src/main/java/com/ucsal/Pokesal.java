package com.ucsal;

public class Pokesal {
    private double hP;

    private double hpBatalha = hP;

    private String nome;

    private String atk;

    private String atk2;

    private int spd;

    private int spdEfeito = spd;

    private ElementosTipagem elementosTipagem;

    private EfeitosStatus statusAtual = null;

    private int turnosSofridos = 0;

    public Pokesal(String nome, double hP, String atk, String atk2,
                   int spd, ElementosTipagem elementosTipagem) {
        this.nome = nome;
        this.hP = hP;
        this.hpBatalha = hP;
        this.atk = atk;
        this.atk2 = atk2;
        this.spd = spd;
        this.spdEfeito = spd;
        this.elementosTipagem = elementosTipagem;
    }

    public void pokesalDanoSofrido(Pokesal atacante) {
       if (hP <= 0) {
       System.out.println("Pokesal derrotado! não pode receber mais dano");
       }

       double danototal = atacante.getElementosTipagem().calculoEfetividade(this.elementosTipagem);

       if (DanoCritico.sortearCritico(atacante)) {
       danototal = DanoCritico.aplicarCritico(danototal);
       }

       hpBatalha = hpBatalha - danototal;
       System.out.println("O dano sofrido foi de: " + danototal);

       System.out.println("A vida restante é de: " + hpBatalha);
    }

    public void contarTurnoEfeito() {
        if (getStatusAtual() != null) {
            if (turnosSofridos < statusAtual.getTurnos()) {
                turnosSofridos += 1;
                // fazer a diferença de sofrer dano ou a redução de
                // velocidade fixa (acho que não é necessário)
                System.out.println("Pokesal " + getNome() + " sofreu os efeitos");
                sofrerDanoEfeitos();
            }
            else {
                if (statusAtual == EfeitosStatus.Paralizado) {
                    spdEfeito = spd;
                }
                System.out.println("Pokesal " + getNome() + " se livrou dos efeito!");
                statusAtual = null;
                turnosSofridos = 0;
            }
        }
    }

    public void sofrerDanoEfeitos() {
        if (statusAtual == EfeitosStatus.Queimado) {
            System.out.println(getNome() +
                    " sofreu com queimadura!E teve sua velocidade reduzida...");
            hpBatalha = hpBatalha - (getHP() * 0.1);
        }
        else if (statusAtual == EfeitosStatus.Envenenado) {
            System.out.println(getNome() + " sofreu com envenenamento");
            hpBatalha = hpBatalha - (getHP() * 0.2);
        }
    }

    public void reduzirVelocidade() {
        if (statusAtual == EfeitosStatus.Paralizado) {
            System.out.println(getNome() + " sofreu com paralisia! Velocidade reduzida...");
            spdEfeito = getSPD() - 10;
        }
        if (statusAtual == EfeitosStatus.Queimado) {
            spdEfeito = getSPD() - 5;
        }
    }

    public String getATK2() {
        return atk2;
    }

    public void setATK2(String atk2) {
        this.atk2 = atk2;
    }

    public String getATK() {
        return atk;
    }

    public void setATK(String atk) {
        this.atk = atk;
    }

    public ElementosTipagem getElementosTipagem() {
        return elementosTipagem;
    }

    public void setElementosTipagem(ElementosTipagem elementosTipagem) {
        this.elementosTipagem = elementosTipagem;
    }

    public double getHP() {
        return hP;
    }

    public void setHP(double hp) {
        this.hP = hp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSPD() {
        return spd;
    }

    public void setSPD(int spd) {
        this.spd = spd;
    }

    public EfeitosStatus getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(EfeitosStatus statusAtual) {
        this.statusAtual = statusAtual;
    }

    public double getHpBatalha() {
        return hpBatalha;
    }

    public void setHpBatalha(double hpBatalha) {
        this.hpBatalha = hpBatalha;
    }

    public int getTurnosSofridos() {
        return turnosSofridos;
    }

    public int getSPDefeito() {
        return spdEfeito;
    }

    public void setSPDefeito(int spdEfeito) {
        this.spdEfeito = spdEfeito;
    }
}
