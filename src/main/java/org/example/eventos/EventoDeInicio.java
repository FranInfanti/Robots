package org.example.eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class EventoDeInicio extends Event {
    public static final EventType<EventoDeInicio> INICIO_DE_JUEGO_EVENT_TYPE = new EventType<>("Inicio De Juego");

    public EventoDeInicio() {
        super(INICIO_DE_JUEGO_EVENT_TYPE);
    }
}
