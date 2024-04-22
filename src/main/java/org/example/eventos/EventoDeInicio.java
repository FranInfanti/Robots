package org.example.eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class EventoDeInicio extends Event {
    private static final String EVENTO = "Inicio de Juego";

    public static final EventType<EventoDeInicio> INICIO_DE_JUEGO_EVENT_TYPE = new EventType<>(EVENTO);

    public EventoDeInicio() {
        super(INICIO_DE_JUEGO_EVENT_TYPE);
    }
}
