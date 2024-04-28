package org.example;

public class Archivo {
    public static final int ALTURA_SCENE = 650;
    public static final int ANCHO_SCENE = 650;
    public static final int ALTO_GRILLA = 400;
    public static final int ANCHO_GRILLA = 400;
    public static final int ESPACIO_CASILLAS = 3;
    public static final int MIN_FILA = 10;
    public static final int MAX_FILA = 30;
    public static final int MIN_COLUMNA = 10;
    public static final int MAX_COLUMNA = 30;
    public static final int INCREMENTO_SLIDER = 5;
    public static final int VISIBLE = 1;

    public String getTextoDeTituloJuego() {
        return "Robots";
    }

    public String getTextoDeInicioJuego() {
        return "Iniciar Juego";
    }

    public String getTextoDeNuevoJuego() {
        return "Nuevo Juego";
    }

    public String getTextoCantidadFilas() {
        return "Cantidad de Filas: ";
    }

    public String getTextoCantidadColumnas() {
        return "Cantidad de Columnas: ";
    }

    public String getTextoDeTeleportAleatorio() {
        return "Teleport\naleatorio";
    }

    public String getTextoDeTeleportSeguros(int teleportsDisponibles) {
        return "Teleport seguro\n(Quedan: %d)" .formatted(teleportsDisponibles);
    }

    public String getTextoEsperarRobots() {
        return "Esperar";
    }

    public String getInstrucciones() {
        return "Seleccione la casilla para la direccion donde quiera ir\nhaciendo clic con el MOUSE o con {<,^,v,>} y presionando ENTER";
    }

    public String getTextoAlertaFinal() {
        return "Eliminado";
    }

    public String getTextoResultado(int nivel, int puntos) {
        return ("Llegaste al Nivel %d\nTu puntaje fue de %d") .formatted(nivel, puntos);
    }

    public String getCssDeTitulo() {
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-font-size: 50; -fx-text-fill: #aaccffff";
    }

    public String getCssTextos() {
        return "-fx-font-family: Arial; -fx-font-weight: 900; -fx-font-size: 14; -fx-text-fill: #aaccffff; -fx-text-alignment: center";
    }

    public String getCssDeBoton(boolean seleccionado) {
        String color = seleccionado ? "#0044aaff" : "#003380ff";
        return "-fx-text-alignment: CENTER; -fx-font-family: Arial; -fx-font-weight: 900; -fx-text-fill: #aaccffff; -fx-font-size: 20; -fx-background-color: %s;  -fx-background-radius: 20; -fx-min-width: 200; -fx-min-height: 65" .formatted(color);
    }

    public String getCssSlider() {
        return "-fx-opacity: 0.9; -fx-max-width: 200; -fx-show-tick-labels: true; -fx-show-tick-marks: true; -fx-major-tick-unit: 5; -fx-minor-tick-count: 5";
    }

    public String getCssCasilla(boolean seleccionado) {
        String color = seleccionado ? "#0044aaff" : "#003380ff";
        return "-fx-background-radius: 20%;" +  "-fx-background-color: %s" .formatted(color);
    }

    public String getCssDeHbox() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff";
    }

    public String getCssDeVbox() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-spacing: 10";
    }

    public String getCssDeVboxJuego() {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-spacing: 20; -fx-min-width: 600; -fx-min-height: 400";
    }

    public String getCssDeGridPane(int minWidth, int minHeight) {
        return "-fx-alignment: CENTER; -fx-background-color: #002255ff; -fx-hgap: 3; -fx-vgap:3; -fx-min-width: %d; -fx-min-height: %d" .formatted(minWidth, minHeight);
    }

    public String getImagenJugador() {
        return "jugador.png";
    }

    public String getImagenEnemigo(int desplazamientoEnemigo) {
        return desplazamientoEnemigo == 0 ? "explosion.png" : "robotX%d.png".formatted(desplazamientoEnemigo);
    }
}