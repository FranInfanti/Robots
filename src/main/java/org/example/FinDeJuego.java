package org.example;

import javafx.event.Event;
import javafx.event.EventType;

public class FinDeJuego extends Event {
    public static final EventType<FinDeJuego> FIN_DE_JUEGO_EVENT_TYPE = new EventType<>();

    public FinDeJuego() {
        super(FIN_DE_JUEGO_EVENT_TYPE);
    }
}
