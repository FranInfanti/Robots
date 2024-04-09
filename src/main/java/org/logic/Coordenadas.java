package org.logic;

public class Coordenadas {
    private final int DERECHA = 1;
    private final int IZQUIERDA = -1;
    private final int ARRIBA = -1;
    private final int ABAJO = 1;

    private int x;
    private int y;

    public Coordenadas(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public Coordenadas compararCoords(Coordenadas otrasCoords){
        Coordenadas resultado = new Coordenadas(0,0);
        if (this.getX() - otrasCoords.getX() > 0){
            resultado.setX(IZQUIERDA);
        }
        if (this.getX() - otrasCoords.getX() < 0){
            resultado.setX(DERECHA);
        }
        if (this.getY() - otrasCoords.getY() > 0){
            resultado.setY(ARRIBA);
        }
        if (this.getY() - otrasCoords.getY() < 0){
            resultado.setY(ABAJO);
        }
        return resultado;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
