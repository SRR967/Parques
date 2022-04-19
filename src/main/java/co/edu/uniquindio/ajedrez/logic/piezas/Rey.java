package co.edu.uniquindio.ajedrez.logic.piezas;

import co.edu.uniquindio.ajedrez.logic.Casilla;
import co.edu.uniquindio.ajedrez.logic.Tablero;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;

public class Rey extends Pieza implements IMover{

    private TipoPieza tipo = TipoPieza.REY;

    public Rey(Color color) {
        super(color);
    }

    public ArrayList<Coordinate> movidas(Pieza pieza) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate = pieza.getCasilla().getCoordinate();
        if (coordinate != null) {
            // Las posibles posiciones del caballo pueden ser obtenidas teniendo en cuenta que las posiciones
            // toman la forma de un circulo espaciado cada 45 grados.
            double radians = Math.toRadians(45.0);
            for (int i = 0; i < 8; i++) {
                int rowPos = coordinate.getRow() + (int) Math.round(Math.sin(radians) * 1.0);
                int colPos = coordinate.getCol() + (int) Math.round(Math.cos(radians) * 1.0);
                radians += Math.toRadians(45.0);

                if (rowPos >= 0 && rowPos <= 7 && colPos >= 0 && colPos <= 7) {
                    coordinates.add(new Coordinate(rowPos, colPos));
                }
            }
        }
        return coordinates;
    }

    /*public ArrayList<Coordinate> movidas(Pieza pieza) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate = pieza.getCasilla().getCoordinate();
        if(coordinate != null){
            int verticalCoordinate = coordinate.getRow();
            int horizontalCoordinate = coordinate.getCol();

            vertical(coordinate, coordinates, verticalCoordinate, horizontalCoordinate);
            horizontal(coordinate, coordinates, verticalCoordinate, horizontalCoordinate);
            diagonalSuperior(coordinate, coordinates, verticalCoordinate, horizontalCoordinate);
            diagonalInferior(coordinate, coordinates, verticalCoordinate, horizontalCoordinate);
        }

        return coordinates;
    }

    private void vertical(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal){

        coordinate.setCol(horizontal);

        for(int i=1; vertical+i <= 1; i++){
            coordinate.setRow(vertical + i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }

        for(int i=1; vertical-i >= 1; i++){
            coordinate.setRow(vertical - i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }
    }

    private void horizontal(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal){

        coordinate.setRow(vertical);

        for(int i=1; horizontal+i <= 1; i++){
            coordinate.setCol(horizontal+i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }
        for(int i=1; horizontal-i >= 1; i++){
            coordinate.setCol(horizontal-i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }
    }

    private void diagonalSuperior(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal) {


        for (int i = 1; horizontal + i <= 1 && vertical + i <= 1; i++) {
            coordinate.setCol(horizontal + i);
            coordinate.setRow(vertical + i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));

        }

        for (int i = 1; horizontal - i >= 0 && vertical + i <= 1; i++) {
            coordinate.setCol(horizontal - i);
            coordinate.setRow(vertical + i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }

    }

    private void diagonalInferior(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal){

        for(int i=1; horizontal-i >= 1 && vertical-i >= 1; i++){
            coordinate.setCol(horizontal-i);
            coordinate.setRow(vertical-i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }

        for(int i=1; horizontal+i <= 1 && vertical-i >= 0; i++){
            coordinate.setCol(horizontal+i);
            coordinate.setRow(vertical-i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }
    }*/

    public ArrayList<Coordinate> filtradas(Pieza pieza, Tablero tablero){
        return movidas(this);
    }

    public String toString() {
        // https://es.wikipedia.org/wiki/S%C3%ADmbolos_de_ajedrez_en_Unicode
        if (this.getColor().equals(Color.BLANCAS)) {
            return "\u2654";
        }
        else {
            return "\u265A";
        }
    }

    @Override
    public void mover(Casilla coordinate) {

    }
}
