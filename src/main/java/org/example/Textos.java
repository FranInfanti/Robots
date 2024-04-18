package org.example;

public class Textos {
    public String getTituloJuego() {
        return "Robots";
    }

    public String getB1texto() {
        return "Teleport Random";
    }

    public String getB2texto(int teleportsDisponibles) {
        return "Teleport Safely\n" +
                " (Remaining: %d)" .formatted(teleportsDisponibles);
    }

    public String getB3texto() {
        return "Wait for robots";
    }
}
