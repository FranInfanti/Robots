package org.example.model.characters;

import org.example.model.Coordenadas;

import java.util.Collection;
import java.util.HashSet;

public class RobotX2 extends Enemigo {
    private static final int DESPLAZAMIENTO_ROBOTX2 = 2;

    public RobotX2(Coordenadas coordenadas) {
        super(coordenadas, DESPLAZAMIENTO_ROBOTX2);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, Collection<?> aux) {
        Coordenadas distanciaMover;
        int x, y;
        for (int i = 0; i < getDesplazamiento() && !getEliminado(); i++) {
            distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
            x = distanciaMover.getX() + getCoordenadas().getX();
            y = distanciaMover.getY() + getCoordenadas().getY();

            setCoordenadas(new Coordenadas(x,y));
            this.isEliminado((HashSet<Enemigo>) aux);
        }
    }
}
