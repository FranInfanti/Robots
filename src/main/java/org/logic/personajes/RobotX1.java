package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.LinkedList;

public class RobotX1 extends Enemigo {
    public RobotX1(Coordenadas coordenadas) {
        super(coordenadas);
    }

    private static final int PUNTAJE_X1 = 10;

    @Override
    public void mover(Coordenadas coordenadasJugador, LinkedList<?> aux) {
        if (this.getEliminado())
            return;

        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
        this.isEliminado((LinkedList<Enemigo>) aux);
    }

    @Override
    public int getPuntos() {
        return PUNTAJE_X1;
    };
}
