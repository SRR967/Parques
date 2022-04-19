package co.edu.uniquindio.ajedrez;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/co/edu/uniquindio/ajedrez/view/board-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 500, 500); //Help
            stage.setTitle("Ajedrez!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();

        /*Tablero tablero = new Tablero();
        System.out.println(tablero);*/

        /*// Validamos los posible movimientos de la pieza dummy negra
        Casilla casilla = tablero.getCasilla(1, 0);
        if (casilla != null) {
            System.out.println(casilla.getCoordinate());
            System.out.println(Dummy.movidas(casilla.getPieza()));
        }*/

        /*// Validamos los posibles movimientos de la pieza dummy blanca
        casilla = tablero.getCasilla(6, 0);
        if (casilla != null) {
            System.out.println(casilla.getCoordinate());
            System.out.println(Dummy.movidas(casilla.getPieza()));
        }*/

        /*Casilla casilla = tablero.getCasilla(0, 0);*/

        /*Pieza pieza = casilla.getPieza(); // Torre negra
        casilla.setPieza(null);
        casilla = tablero.getCasilla(4, 4);
        casilla.setPieza(pieza);
        pieza.setCasilla(casilla);*/

        /*casilla = tablero.getCasilla(0, 2); // Alfil negro
        Pieza pieza = casilla.getPieza();
        casilla.setPieza(null);
        casilla = tablero.getCasilla(2, 6);
        casilla.setPieza(pieza);
        pieza.setCasilla(casilla);*/

        /*casilla = tablero.getCasilla(0, 3); // Reina negra
        pieza = casilla.getPieza();
        casilla.setPieza(null);
        casilla = tablero.getCasilla(3, 2);
        casilla.setPieza(pieza);
        pieza.setCasilla(casilla);*/

        /*casilla = tablero.getCasilla(1, 2); // Peon negra
        pieza = casilla.getPieza();*/

        /*casilla = tablero.getCasilla(6, 4); // Peon blanco
        pieza = casilla.getPieza();*/

        /*casilla = tablero.getCasilla(0, 1); // Caballo negro
        pieza = casilla.getPieza();
        casilla.setPieza(null);
        casilla = tablero.getCasilla(4, 4);
        casilla.setPieza(pieza);
        pieza.setCasilla(casilla);*/

        /*casilla = tablero.getCasilla(0, 4); // Rey negro
        pieza = casilla.getPieza();
        casilla.setPieza(null);
        casilla = tablero.getCasilla(4, 5);
        casilla.setPieza(pieza);
        pieza.setCasilla(casilla);
*/
        /*if (casilla != null) {
            System.out.println(casilla.getCoordinate());
            if (pieza instanceof Pieza) {
                System.out.println(pieza.movidas(casilla.getPieza()));
            }
        }

        System.out.println(tablero);*/
    }
}