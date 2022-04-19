package co.edu.uniquindio.ajedrez.logic;

import co.edu.uniquindio.ajedrez.logic.piezas.*;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;

public class Tablero {

    private Casilla casillas[][] = new Casilla[8][8];

    public Tablero() {
        for (int row = 0; row < casillas.length; row++) {
            for (int columm = 0; columm < casillas[row].length; columm++) {
                casillas[row][columm] =  new Casilla(new Coordinate(row, columm));
            }
        }
        this.iniciar();
    }

    public void iniciar() {
        Color color = Color.NEGRAS;

        char[] rows = "87654321".toCharArray();
        char[] cols = "abcdefgh".toCharArray();

        for (int row = 0; row < casillas.length; row++) {
            // Usamos la siguiente condicion para evitar procesar filas sin piezas.
            if (row > 1 && row < 6) {
                continue;
            }

            if (row > 3) {
                color = Color.BLANCAS;
            }

            for (int columm = 0; columm < casillas[row].length; columm++) {
                Casilla casilla = new Casilla(new Coordinate(row, columm));
                // Definimos peones.
                if (row == 1 || row == 6) {
                    casilla.setPieza(new Peon(color));
                }

                // Definimos torres.
                if ((row == 0 || row == 7) && (columm == 0 || columm == 7)) {
                    casilla.setPieza(new Torre(color));
                }

                // Definimos caballos.
                if ((row == 0 || row == 7) && (columm == 1 || columm == 6)) {
                    casilla.setPieza(new Caballo(color));
                }

                // Definimos Alfiles.
                if ((row == 0 || row == 7) && (columm == 2 || columm == 5)) {
                    casilla.setPieza(new Alfil(color));
                }

                // Definimos reinas.
                if ((row == 0 || row == 7) && (columm == 4)) {
                    casilla.setPieza(new Reina(color));
                }

                // Definimos reyes.
                if ((row == 0 || row == 7) && (columm == 3)) {
                    casilla.setPieza(new Rey(color));
                }

                casilla.setCoordinate(new Coordinate(row, columm));
                // Le indicamos a la casilla el tablero donde esta ubicado.
                casilla.setTablero(this);

                if (!casilla.getPieza().equals(null)) {
                    // Generamos una relacion inversa en la pieza, que nos permite desde la pieza conocer en que casilla
                    // esta.
                    casilla.getPieza().setCasilla(casilla);
                }

                casillas[row][columm] =  casilla;
            }
        }
    }

    public ArrayList<Casilla> posiblesMovidas(Casilla casilla) {
        ArrayList<Casilla> casillas = new ArrayList<>();
        if (casilla.getPieza() != null && casilla.getPieza() instanceof IMover) {
            IMover pieza = (IMover) casilla.getPieza();
            pieza.mover(casilla);
        }
        return casillas;
    }

    public Casilla getCasilla(int row, int col) {
        Casilla casilla = null;
        if (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
            return casillas[row][col];
        }
        return casilla;
    }

    public String toString() {
        String resultado = "";
        for (int row = 0; row < casillas.length; row++) {
            String line = "";
            for (int columm = 0; columm < casillas[row].length; columm++) {
                line += " " + casillas[row][columm].toString() + " ";
            }
            resultado += line + "\n";
        }
        return resultado;
    }
}
