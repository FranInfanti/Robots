package org.example.model.characters;

import org.example.model.Coordenadas;
import java.util.Collection;
import java.util.HashSet;

public class RobotX1 extends Enemigo {
    private static final int DESPLAZAMIENTO_ROBOTX1 = 1;

    public RobotX1(Coordenadas coordenadas) {
        super(coordenadas, DESPLAZAMIENTO_ROBOTX1);
    }

    @Override
    public void mover(Coordenadas coordenadasJugador, Collection<?> aux) {
        if (this.getEliminado())
            return;

        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
        this.isEliminado((HashSet<Enemigo>) aux);
    }
}
