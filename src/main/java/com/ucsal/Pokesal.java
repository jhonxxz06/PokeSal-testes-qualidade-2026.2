package com.ucsal;

/**
 * Criatura que participa das batalhas, com vida, velocidade, ataques, tipo, status
 * e efeitos temporários de poção.
 */
public class Pokesal {

    /**
     * Vida máxima do Pokesal.
     */
    private double hP;

    /**
     * Vida atual do Pokesal durante a batalha.
     */
    private double hpBatalha = hP;

    /**
     * Nome do Pokesal.
     */
    private String nome;

    /**
     * Nome do primeiro ataque.
     */
    private String atk;

    /**
     * Nome do segundo ataque.
     */
    private String atk2;

    /**
     * Velocidade base do Pokesal.
     */
    private int spd;

    /**
     * Velocidade atual, considerando os efeitos de status e de poção.
     */
    private int spdEfeito = spd;

    /**
     * Tipo (elemento) do Pokesal.
     */
    private ElementosTipagem elementosTipagem;

    /**
     * Efeito de status atual, ou {@code null} quando não há efeito.
     */
    private EfeitosStatus statusAtual = null;

    /**
     * Quantidade de turnos em que o Pokesal já sofreu o efeito de status atual.
     */
    private int turnosSofridos = 0;

    /**
     * Multiplicador de força causado pela poção (1.0 quando não há poção ativa).
     */
    private double forcaEfeito = 1.0;

    /**
     * Turnos que restam do efeito da poção ativa.
     */
    private int turnosPocao = 0;

    /**
     * Indica se a poção foi usada no turno atual, para o efeito valer no turno seguinte.
     */
    private boolean pocaoUsadaNesteTurno = false;

    /**
     * Cria um Pokesal.
     *
     * @param nome             nome do Pokesal
     * @param hP               vida máxima
     * @param atk              nome do primeiro ataque
     * @param atk2             nome do segundo ataque
     * @param spd              velocidade
     * @param elementosTipagem tipo (elemento) do Pokesal
     */
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

    /**
     * Recebe o ataque do atacante, considerando a efetividade entre os tipos, a força
     * da poção e a chance de golpe crítico, e desconta o dano da vida atual.
     *
     * @param atacante Pokesal que está atacando
     */
    public void pokesalDanoSofrido(Pokesal atacante) {
        if (hP <= 0) {
            System.out.println("Pokesal derrotado! não pode receber mais dano");
        }

        double danototal = atacante.getElementosTipagem().calculoEfetividade(this.elementosTipagem);
        danototal = danototal * atacante.getForcaEfeito();

        if (ElementosTipagem.sortearCritico(atacante)) {
            danototal = ElementosTipagem.aplicarCritico(danototal);
        }

        hpBatalha = hpBatalha - danototal;
        System.out.println("O dano sofrido foi de: " + danototal);

        System.out.println("A vida restante é de: " + hpBatalha);
    }

    /**
     * Conta um turno do efeito de status atual, aplicando o dano do efeito
     * ou removendo o efeito quando a duração termina.
     */
    public void contarTurnoEfeito() {
        if (getStatusAtual() != null) {
            if (turnosSofridos < statusAtual.getTurnos()) {
                turnosSofridos += 1;
                // fazer a diferença de sofrer dano ou a redução de
                // velocidade fixa (acho que não é necessário)
                System.out.println("Pokesal " + getNome() + " sofreu os efeitos");
                sofrerDanoEfeitos();
            } else {
                if (statusAtual == EfeitosStatus.Paralizado) {
                    spdEfeito = spd;
                }
                System.out.println("Pokesal " + getNome() + " se livrou dos efeito!");
                statusAtual = null;
                turnosSofridos = 0;
            }
        }
    }

    /**
     * Aplica a poção de fúria: aumenta a força em 10% e a velocidade em 20% por um turno.
     */
    public void aplicarPocaoFuria() {
        forcaEfeito = 1.10;
        spdEfeito = (int) (spd * 1.20);
        turnosPocao = 1;
        pocaoUsadaNesteTurno = true;

        System.out.println(getNome() + " usou a Poção de Fúria!");
        System.out.println("Força aumentada em 10% e velocidade em 20% por 1 turno.");
    }

    /**
     * Aplica a poção de força: aumenta a força em 25% por um turno.
     */
    public void aplicarPocaoForca() {
        forcaEfeito = 1.25;
        turnosPocao = 1;
        pocaoUsadaNesteTurno = true;

        System.out.println(getNome() + " usou a Poção de Força!");
        System.out.println("Força aumentada em 25% por 1 turno.");
    }

    /**
     * Conta um turno do efeito da poção. No turno em que a poção é usada o efeito
     * é mantido; no turno seguinte ele termina e força e velocidade voltam ao normal.
     */
    public void contarTurnoPocao() {
        if (pocaoUsadaNesteTurno) {
            pocaoUsadaNesteTurno = false;
            return;
        }

        if (turnosPocao > 0) {
            turnosPocao = 0;
            forcaEfeito = 1.0;
            spdEfeito = spd;
            System.out.println("O efeito da poção acabou!");
        }
    }

    /**
     * Retorna o multiplicador de força causado pela poção.
     *
     * @return multiplicador de força, ou 1.0 se não houver poção ativa
     */
    public double getForcaEfeito() {
        return forcaEfeito;
    }

    /**
     * Aplica o dano por turno de queimadura ou envenenamento.
     */
    public void sofrerDanoEfeitos() {
        if (statusAtual == EfeitosStatus.Queimado) {
            System.out.println(getNome() +
                    " sofreu com queimadura!E teve sua velocidade reduzida...");
            hpBatalha = hpBatalha - (getHP() * 0.1);
        } else if (statusAtual == EfeitosStatus.Envenenado) {
            System.out.println(getNome() + " sofreu com envenenamento");
            hpBatalha = hpBatalha - (getHP() * 0.2);
        }
    }

    /**
     * Reduz a velocidade atual conforme o efeito de status (paralisia ou queimadura).
     */
    public void reduzirVelocidade() {
        if (statusAtual == EfeitosStatus.Paralizado) {
            System.out.println(getNome() + " sofreu com paralisia! Velocidade reduzida...");
            spdEfeito = getSPD() - 10;
        }
        if (statusAtual == EfeitosStatus.Queimado) {
            spdEfeito = getSPD() - 5;
        }
    }

    /**
     * Retorna o nome do segundo ataque.
     *
     * @return nome do segundo ataque
     */
    public String getATK2() {
        return atk2;
    }

    /**
     * Altera o nome do segundo ataque.
     *
     * @param atk2 novo nome do segundo ataque
     */
    public void setATK2(String atk2) {
        this.atk2 = atk2;
    }

    /**
     * Retorna o nome do primeiro ataque.
     *
     * @return nome do primeiro ataque
     */
    public String getATK() {
        return atk;
    }

    /**
     * Altera o nome do primeiro ataque.
     *
     * @param atk novo nome do primeiro ataque
     */
    public void setATK(String atk) {
        this.atk = atk;
    }

    /**
     * Retorna o tipo (elemento) do Pokesal.
     *
     * @return tipo do Pokesal
     */
    public ElementosTipagem getElementosTipagem() {
        return elementosTipagem;
    }

    /**
     * Altera o tipo (elemento) do Pokesal.
     *
     * @param elementosTipagem novo tipo do Pokesal
     */
    public void setElementosTipagem(ElementosTipagem elementosTipagem) {
        this.elementosTipagem = elementosTipagem;
    }

    /**
     * Retorna a vida máxima.
     *
     * @return vida máxima
     */
    public double getHP() {
        return hP;
    }

    /**
     * Altera a vida máxima.
     *
     * @param hp nova vida máxima
     */
    public void setHP(double hp) {
        this.hP = hp;
    }

    /**
     * Retorna o nome do Pokesal.
     *
     * @return nome do Pokesal
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome do Pokesal.
     *
     * @param nome novo nome do Pokesal
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna a velocidade base.
     *
     * @return velocidade base
     */
    public int getSPD() {
        return spd;
    }

    /**
     * Altera a velocidade base.
     *
     * @param spd nova velocidade base
     */
    public void setSPD(int spd) {
        this.spd = spd;
    }

    /**
     * Retorna o efeito de status atual.
     *
     * @return efeito de status atual, ou {@code null} se não houver
     */
    public EfeitosStatus getStatusAtual() {
        return statusAtual;
    }

    /**
     * Altera o efeito de status atual.
     *
     * @param statusAtual novo efeito de status
     */
    public void setStatusAtual(EfeitosStatus statusAtual) {
        this.statusAtual = statusAtual;
    }

    /**
     * Retorna a vida atual na batalha.
     *
     * @return vida atual
     */
    public double getHpBatalha() {
        return hpBatalha;
    }

    /**
     * Altera a vida atual na batalha.
     *
     * @param hpBatalha nova vida atual
     */
    public void setHpBatalha(double hpBatalha) {
        this.hpBatalha = hpBatalha;
    }

    /**
     * Retorna quantos turnos o Pokesal já sofreu o efeito de status atual.
     *
     * @return turnos sofridos
     */
    public int getTurnosSofridos() {
        return turnosSofridos;
    }

    /**
     * Retorna a velocidade atual, considerando os efeitos de status e de poção.
     *
     * @return velocidade atual
     */
    public int getSPDefeito() {
        return spdEfeito;
    }

    /**
     * Altera a velocidade atual, considerando os efeitos de status e de poção.
     *
     * @param spdEfeito nova velocidade atual
     */
    public void setSPDefeito(int spdEfeito) {
        this.spdEfeito = spdEfeito;
    }
}
