package org.example.eventos;

import javafx.event.Event;
import javafx.event.EventType;

public class EventoDeFin extends Event {
    public static final EventType<EventoDeFin> FIN_DE_JUEGO_EVENT_TYPE = new EventType<>("Fin De Juego");

    public EventoDeFin() {
        super(FIN_DE_JUEGO_EVENT_TYPE);
    }
}
