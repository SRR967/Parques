package co.edu.uniquindio.ajedrez.logic.piezas;

import co.edu.uniquindio.ajedrez.logic.Casilla;
import co.edu.uniquindio.ajedrez.logic.Tablero;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;

public class Alfil extends Pieza implements IMover{

    private TipoPieza tipo = TipoPieza.ALFIL;

    public Alfil(Color color) {
        super(color);
    }

    @Override
    public void mover(Casilla coordinate) {
        // Tomamos de la ficha actual la casilla y el tablero de la casilla, esto nos permitira validar si la ficha
        // puede moverse, comer, reclamar o hacer enroque.
        Coordinate piezaCoor = this.getCasilla().getCoordinate();
        int row = coordinate.getCoordinate().getRow();
        int col = coordinate.getCoordinate().getCol();
        boolean posible1 = false;

        if(this.getCasilla().getTablero().getCasilla(row, col).getPieza().getColor() != this.getColor()) {
            for(Coordinate i: movidas(this)) {
                if(i.getRow() == row && i.getCol() == col) {
                    posible1 = true;
                }
            }
        }

        if(posible1) {

            int i, j;
            for(i=row-1, j=col-1; i>piezaCoor.getRow() && j>piezaCoor.getCol(); i--, j--) {
                if(this.getCasilla().getTablero().getCasilla(i, j) != null) {
                    posible1 = false;
                    break;
                }
            }
            for(i=row-1, j=col-1; i<piezaCoor.getRow() && j<piezaCoor.getCol(); i++, j++) {
                if(this.getCasilla().getTablero().getCasilla(i, j) != null) {
                    posible1 = false;
                    break;
                }
            }
            for(i=row-1, j=col-1; i<piezaCoor.getRow() && j>piezaCoor.getCol(); i++, j--) {
                if(this.getCasilla().getTablero().getCasilla(i, j) != null) {
                    posible1 = false;
                    break;
                }
            }
            for(i=row-1, j=col-1; i>piezaCoor.getRow() && j<piezaCoor.getCol(); i--, j++) {
                if(this.getCasilla().getTablero().getCasilla(i, j) != null) {
                    posible1 = false;
                    break;
                }
            }
        }
        if(posible1) {
            if(this.getCasilla().getTablero().getCasilla(row, col).getPieza() != null) {
                this.getCasilla().getTablero().getCasilla(row, col).getPieza().setCasilla(null);
            }
            this.getCasilla().getTablero().getCasilla(row, col).setPieza(this);
            this.getCasilla().setPieza(null);
            this.setCasilla(this.getCasilla().getTablero().getCasilla(row, col));
        }
    }

    public ArrayList<Coordinate> movidas(Pieza pieza) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate = pieza.getCasilla().getCoordinate();
        if (coordinate != null) {
            // Las posibles posiciones del caballo pueden ser obtenidas teniendo en cuenta que las posiciones
            // toman la forma de un circulo espaciado cada 45 grados.
            double radians = Math.toRadians(45.0);
            for (int j = 1; j < 8; j++) {
                for (int i = 0; i < 4; i++) {
                    int rowPos = coordinate.getRow() + (int) Math.round(Math.sin(radians) * ((double) j));
                    int colPos = coordinate.getCol() + (int) Math.round(Math.cos(radians) * ((double) j));
                    radians += Math.toRadians(90.0);

                    if (rowPos >= 0 && rowPos <= 7 && colPos >= 0 && colPos <= 7) {
                        coordinates.add(new Coordinate(rowPos, colPos));
                    }
                }
            }
        }
        return coordinates;
    }

    /*public ArrayList<Coordinate> movidas(Pieza pieza) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate = pieza.getCasilla().getCoordinate();
        if (coordinate != null) {
            for (int i = -8; i < 8; i++) {
                int row = i + coordinate.getRow();
                int col = i + coordinate.getCol();
                if (row >= 0 && row <= 7 && col >= 0 && col <= 7 && row != coordinate.getRow() && col != coordinate.getCol()) {
                    Coordinate coordenada = new Coordinate(row, col);
                    coordinates.add(coordenada);
                }

                int col1 = coordinate.getCol() - i;
                if (row >= 0 && row <= 7 && col1 >= 0 && col1 <= 7 && row != coordinate.getRow() && col1 != coordinate.getCol()) {
                    Coordinate coordenada = new Coordinate(row, col1);
                    coordinates.add(coordenada);
                }
            }
        }
        return coordinates;
    }*/

    public ArrayList<Coordinate> filtradas(Pieza pieza, Tablero tablero){
        return movidas(this);
    }

    public String toString() {
        // https://es.wikipedia.org/wiki/S%C3%ADmbolos_de_ajedrez_en_Unicode
        if (this.getColor().equals(Color.BLANCAS)) {
            return "\u2657";
        }
        else {
            return "\u265D";
        }
    }
}
