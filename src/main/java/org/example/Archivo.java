package org.example;

public class Archivo {
    public String getTextoDeTituloJuego() {
        return "Robots";
    }

    public String getTextoDeInicioJuego() {
        return "Iniciar Juego";
    }

    public String getTextoDeNuevoJuego() {
        return "Nuevo Juego";
    }

    public String getTextoDeTeleportRandom() {
        return "Teleport Random";
    }

    public String getTextoComoJugar() {
        return "Seleccione la casilla donde quiera ir, clic con el MOUSE o moviendo con las teclas y presionando ENTER";
    }

    public String getTextoCantFilas() {
        return "Cantidad de Filas \n10          15          20          25          30";
    }

    public String getTextoCantColumnas() {
        return "Cantidad de Columnas \n10          15          20          25          30";
    }

    public String getTextoDeTeleportSafely(int teleportsDisponibles) {
        return "Teleport Safely\n  (Remaining: %d)" .formatted(teleportsDisponibles);
    }

    public String getTextoPuntos(int puntos) {
        return "Puntos: %d" .formatted(puntos);
    }

    public String getTextoDeWaitForRobots() {
        return "Wait for robots";
    }

    public String getTextoAlertaFinal() {
        return "Gracias Por Jugar :)";
    }

    public String getTextoResultado(int nivel, int puntos) {
        return ("Llegaste al Nivel %d\n Tu puntaje fue de %d") .formatted(nivel, puntos);
    }

    public String getCssDeTitulo() {
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-font-size: 50; -fx-text-fill: #aaccffff";
    }

    public String getCssTextos() {
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-font-size: 12; -fx-text-fill: #aaccffff; -fx-text-alignment: center";
    }

    public String getCssDeBoton(boolean seleccionado) {
        String color = seleccionado ? "#0044aaff" : "#003380ff";
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-text-fill: #aaccffff; -fx-font-size: 20; -fx-background-color: %s;  -fx-background-radius: 20; -fx-min-width: 200; -fx-min-height: 65" .formatted(color);
    }

    public String getCssSlider(boolean seleccionado) {
        String opacidad = seleccionado ? "0.1" : "0.2";
        return "-fx-opacity: %s; -fx-max-width: 200" .formatted(opacidad);
    }

    public String getCssCasilla(boolean seleccionado) {
        String color = seleccionado ? "#0044aaff" : "#003380ff";
        return "-fx-background-color: "+ color +"; -fx-background-radius: 20%";
    }

    public String getCssDeHbox() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff";
    }

    public String getCssDeVbox(boolean activado) {
        int opacidad = activado ? 1 : 0;
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-spacing: 10; -fx-opacity: %d" .formatted(opacidad);
    }

    public String getCssDeVboxJuego() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-spacing: 20; -fx-min-width: 600; -fx-min-height: 400";
    }

    public String getCssDeGridPane(int minWidth, int minHeight) {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-hgap: 3; -fx-vgap: 3; -fx-min-width: %d; -fx-min-height: %d" .formatted(minWidth, minHeight);
    }

    public String getImagenJugador() {
        return "jugador.png";
    }

    public String getImagenRobotX1() {
        return "robotX1.png";
    }

    public String getImagenRobotX2() {
        return "robotX2.png";
    }

    public String getImagenExplosion() {
        return "explosion.png";
    }

    public String getEstiloSliderSelec() { return "-fx-opacity: 0.1"; }

    public String getEstiloDeBotonSelec() { return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-text-fill: #aaccffff; -fx-font-size: 20; -fx-background-color: #0044aaff;  -fx-background-radius: 20;";
    }

    public String getEstiloCasillaSelec() {  return "-fx-cursor: pointer; -fx-background-color: #0044aaff;  -fx-background-radius: ";
    }
}
