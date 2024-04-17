package org.logic.personajes;

import org.logic.Coordenadas;
import java.util.LinkedList;

public class Jugador extends Personaje {
    private int teleportsSeguros;

    public Jugador(Coordenadas coordenadas, int teleportsDisponibles) {
        super(coordenadas);
        this.teleportsSeguros = teleportsDisponibles;
    }

    @Override
    public void mover(Coordenadas coordenadas, LinkedList<?> aux) {
        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadas);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
    }

    /*
    @Override
    public void isEliminado(LinkedList<Enemigo> enemigos) {
        for (Enemigo enemigo : enemigos) {
            if (getCoordenadas().esIgual(enemigo.getCoordenadas()))
                setEliminado(true);
        }
    }
    */

    public void teleportSeguro(Coordenadas coordenadas) {
        if (teleportsSeguros == 0)
            return;

        setCoordenadas(coordenadas);
        teleportsSeguros--;
    }

    public void teleportAleatorio(Coordenadas coordenadas) {
        setCoordenadas(coordenadas);
    }

    public void setTeleportsSeguros(int teleportsSeguros) {
        this.teleportsSeguros = teleportsSeguros;
    }

    public int getTeleportsSeguros() {
        return teleportsSeguros;
    }
}
