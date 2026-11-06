package com.ucsal;

import java.time.Clock;
import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Decide se o efeito de um terreno está ativo, considerando o clima e o horário.
 * O horário vem de um {@link Clock} e o clima de um {@link Supplier}, ambos
 * recebidos pelo construtor, o que permite fixá-los nos testes.
 *
 * <ul>
 *   <li>Asfalto Quente: ativo somente durante o dia (06:00 a 17:59).</li>
 *   <li>Piso Escorregadio: ativo somente com clima chuvoso.</li>
 *   <li>Canteiro Central: sempre ativo.</li>
 * </ul>
 */
public class ServicoTerreno {

    /** Condições climáticas possíveis durante uma batalha. */
    public enum Clima {
        ENSOLARADO,
        NUBLADO,
        CHUVOSO
    }

    private static final LocalTime INICIO_DIA = LocalTime.of(6, 0);
    private static final LocalTime FIM_DIA = LocalTime.of(18, 0);

    private final Clock clock;
    private final Supplier<Clima> fonteClima;

    /**
     * Cria o serviço.
     *
     * @param clock      relógio usado para saber o horário
     * @param fonteClima fornecedor do clima atual
     */
    public ServicoTerreno(Clock clock, Supplier<Clima> fonteClima) {
        this.clock = Objects.requireNonNull(clock, "clock");
        this.fonteClima = Objects.requireNonNull(fonteClima, "fonteClima");
    }

    /**
     * Cria um serviço com o relógio do sistema e clima sorteado a cada consulta.
     *
     * @return serviço padrão
     */
    public static ServicoTerreno padrao() {
        final Clima[] climas = Clima.values();
        return new ServicoTerreno(Clock.systemDefaultZone(),
                () -> climas[ThreadLocalRandom.current().nextInt(climas.length)]);
    }

    /**
     * Indica se agora é dia (das 06:00 até antes das 18:00).
     *
     * @return {@code true} se for dia
     */
    public boolean ehDia() {
        final LocalTime agora = LocalTime.now(clock);
        return !agora.isBefore(INICIO_DIA) && agora.isBefore(FIM_DIA);
    }

    /**
     * Indica se o efeito do terreno está ativo neste momento.
     *
     * @param terreno terreno da batalha
     * @return {@code true} se o efeito do terreno deve ser aplicado
     */
    public boolean efeitoAtivo(AsfaltoUcsal terreno) {
        switch (terreno) {
            case ASFALTO_QUENTE:
                return ehDia();
            case PISO_ESCORREGADIO:
                return fonteClima.get() == Clima.CHUVOSO;
            case CANTEIRO_CENTRAL:
                return true;
            default:
                return false;
        }
    }
}
