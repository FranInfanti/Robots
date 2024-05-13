package org.example.eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class EventoDeFin extends Event {
    private static final String EVENTO = "Fin de Juego";

    public static final EventType<EventoDeFin> FIN_DE_JUEGO_EVENT_TYPE = new EventType<>(EVENTO);

    public EventoDeFin() {
        super(FIN_DE_JUEGO_EVENT_TYPE);
    }
}
