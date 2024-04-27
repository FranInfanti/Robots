package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.Collection;

public class Explosion extends Enemigo {
    public Explosion(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas jugador, Collection<?> aux) {
    }

    @Override
    public int getDesplazamiento() {
        return 0;
    }
}
