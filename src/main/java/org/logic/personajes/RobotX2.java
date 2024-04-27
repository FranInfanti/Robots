package org.logic.personajes;

import org.logic.Coordenadas;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

public class RobotX2 extends Enemigo {
    private static final int DEZSPLAZAMIENTO = 2;

    public RobotX2(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, Collection<?> aux) {
        Coordenadas distanciaMover;
        int x, y;
        for (int i = 0; i < DEZSPLAZAMIENTO && !getEliminado(); i++) {
            distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
            x = distanciaMover.getX() + getCoordenadas().getX();
            y = distanciaMover.getY() + getCoordenadas().getY();

            setCoordenadas(new Coordenadas(x,y));
            this.isEliminado((HashSet<Enemigo>) aux);
        }
    }

    @Override
    public int getDesplazamiento() {
        return DEZSPLAZAMIENTO;
    }
}
