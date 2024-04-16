package org.logic.personajes;

import org.logic.Coordenadas;

import java.util.ArrayList;

public class Obstaculo extends Enemigo {
    public Obstaculo(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, ArrayList<?> aux) {
    }
}
