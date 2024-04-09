package org.logic;

public class RobotX2 extends Enemigo {
    private final int DEZSPLAZAMIENTO = 2;

    public RobotX2(Coordenadas posicionInicial) {
        super(posicionInicial);
    }

    @Override
    public void moverEnemigo(Coordenadas coordenadasJugador) {
        Coordenadas distanciaMover;
        int x, y;
        for (int i = 0; i < DEZSPLAZAMIENTO; i++) {
            distanciaMover = getCoordenadas().calcularDesplazamiento(coordenadasJugador);
            x = distanciaMover.getX() + getCoordenadas().getX();
            y = distanciaMover.getY() + getCoordenadas().getY();
            setCoordenadas(new Coordenadas(x,y));
        }
    }
}
