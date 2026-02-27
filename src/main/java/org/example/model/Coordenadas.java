package org.example.model;

public class Coordenadas {
    private static final int DERECHA = 1;
    private static final int IZQUIERDA = -1;
    private static final int ARRIBA = -1;
    private static final int ABAJO = 1;

    private int x;
    private int y;

    public Coordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordenadas calcularDesplazamiento(Coordenadas coordenadas) {
        Coordenadas resultado = new Coordenadas(0,0);
        if (x - coordenadas.getX() > 0)
            resultado.setX(IZQUIERDA);

        if (x - coordenadas.getX() < 0)
            resultado.setX(DERECHA);

        if (y - coordenadas.getY() > 0)
            resultado.setY(ARRIBA);

        if (y - coordenadas.getY() < 0)
            resultado.setY(ABAJO);

        return resultado;
    }

    public boolean esIgual(Coordenadas coordenadas){
        return this.getX() == coordenadas.getX() && this.getY() == coordenadas.getY();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
