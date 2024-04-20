package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.LinkedList;

public class RobotX2 extends Enemigo {
    private static final int DEZSPLAZAMIENTO = 2;
    private static final int PUNTAJE_X2 = 20;

    public RobotX2(Coordenadas coordenadas) {
        super(coordenadas);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, LinkedList<?> aux) {
        Coordenadas distanciaMover;
        int x, y;
        for (int i = 0; i < DEZSPLAZAMIENTO && !getEliminado(); i++) {
            distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
            x = distanciaMover.getX() + getCoordenadas().getX();
            y = distanciaMover.getY() + getCoordenadas().getY();

            setCoordenadas(new Coordenadas(x,y));
            this.isEliminado((LinkedList<Enemigo>) aux);
        }
    }

    @Override
    public int getPuntos() {
        return PUNTAJE_X2;
    };
}

