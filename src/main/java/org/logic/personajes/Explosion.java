package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.Collection;

public class Explosion extends Enemigo {
    private static final String IMAGEN = "explosion.png";

    public Explosion(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, Collection<?> aux) {
    }

    @Override
    public int getPuntaje() {
        return 0;
    }

    @Override
    public String getImagen() {
        return IMAGEN;
    }
}
