package org.logic;

import org.logic.personajes.Enemigo;
import org.logic.personajes.RobotX1;
import org.logic.personajes.RobotX2;

public class SistemaPuntaje {
    private int puntos;

    public int getPuntos() {
        return puntos;
    }

    public void sumarPuntos(int puntosEliminado) {
        puntos += puntosEliminado;
    }

    public SistemaPuntaje() {
        puntos = 0;
    }
}
