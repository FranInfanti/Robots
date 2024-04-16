package org.logic.personajes;

import org.logic.Coordenadas;

import java.util.ArrayList;

public class RobotX2 extends Enemigo {
    private final int DEZSPLAZAMIENTO = 2;

    public RobotX2(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, ArrayList<?> aux) {
        Coordenadas distanciaMover;
        int x, y;
        for (int i = 0; i < DEZSPLAZAMIENTO && !getEliminado(); i++) {
            distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
            x = distanciaMover.getX() + getCoordenadas().getX();
            y = distanciaMover.getY() + getCoordenadas().getY();

            setCoordenadas(new Coordenadas(x,y));
            this.isEliminado(aux);
        }
    }
}
