package org.logic;

public class RobotX1 extends Enemigo {
    public RobotX1(Coordenadas coordenadasInicial) {
        super(coordenadasInicial);
    }

    // El Robot se mueve segun como se movio el Jugador
    @Override
    public void moverEnemigo(Coordenadas coordenadasJugador) {
        Coordenadas distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
        int x = distanciaMover.getX() + getCoordenadas().getX();
        int y = distanciaMover.getY() + getCoordenadas().getY();
        setCoordenadas(new Coordenadas(x,y));
    }
}
