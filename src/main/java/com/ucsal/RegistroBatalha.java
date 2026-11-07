package com.ucsal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Registro dos eventos da batalha. Em cada turno os eventos são guardados e, ao
 * encerrar o turno, passam para o histórico na ordem: ataque, dano, efeito de
 * status, efeito de terreno e uso de item.
 */
public class RegistroBatalha {

    /**
     * Tipos de evento, declarados na ordem em que entram no histórico do turno.
     */
    public enum Tipo {
        ATAQUE,
        DANO,
        EFEITO_STATUS,
        EFEITO_TERRENO,
        USO_ITEM
    }

    /**
     * Evento ocorrido em um turno.
     *
     * @param turno número do turno (começa em 1)
     * @param tipo tipo do evento
     * @param descricao texto descrevendo o que aconteceu
     */
    public record Evento(int turno, Tipo tipo, String descricao) {
    }

    private final List<Evento> pendentes = new ArrayList<>();
    private final List<Evento> historico = new ArrayList<>();

    /**
     * Registra um evento do turno atual.
     *
     * @param turno número do turno
     * @param tipo tipo do evento
     * @param descricao texto descrevendo o que aconteceu
     */
    public void registrar(int turno, Tipo tipo, String descricao) {
        pendentes.add(new Evento(turno, tipo, descricao));
    }

    /**
     * Encerra o turno: ordena os eventos pendentes pela ordem de {@link Tipo}
     * (a ordenação é estável) e os move para o histórico.
     */
    public void encerrarTurno() {
        pendentes.sort(Comparator.comparing(Evento::tipo));
        historico.addAll(pendentes);
        pendentes.clear();
    }

    /**
     * Retorna os eventos dos turnos já encerrados.
     *
     * @return lista imutável, na ordem de registro
     */
    public List<Evento> getEventos() {
        return Collections.unmodifiableList(historico);
    }

    /**
     * Monta o texto do histórico, com um evento por linha.
     *
     * @return histórico dos turnos já encerrados
     */
    public String resumo() {
        if (historico.isEmpty()) {
            return "Nenhum evento registrado.";
        }
        final StringBuilder texto = new StringBuilder("Histórico da batalha:");
        for (Evento evento : historico) {
            texto.append("\nTurno ").append(evento.turno())
                    .append(" [").append(evento.tipo()).append("] ")
                    .append(evento.descricao());
        }
        return texto.toString();
    }
}