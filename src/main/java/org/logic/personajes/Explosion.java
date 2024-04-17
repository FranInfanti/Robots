package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.LinkedList;

public class Explosion extends Enemigo {
    public Explosion(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, LinkedList<?> aux) {
    }
}
