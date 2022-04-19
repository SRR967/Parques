package co.edu.uniquindio.ajedrez.logic.piezas;

import co.edu.uniquindio.ajedrez.logic.Casilla;
import co.edu.uniquindio.ajedrez.logic.Tablero;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;

public class Reina extends Pieza implements IMover{

    private TipoPieza tipo = TipoPieza.REINA;

    public Reina(Color color) {
        super(color);
    }

    @Override
    public void mover(Casilla destino) {
        Tablero tablero = destino.getTablero();
        ArrayList<Coordinate> coordinates = movidas(destino.getPieza());

        for (Coordinate cordenada : coordinates) {
            if(tablero.getCasilla(cordenada.getRow(), cordenada.getCol()).toString() == "\u2591" ||
                    tablero.getCasilla(cordenada.getRow(), cordenada.getCol()).toString() == "\u2593"){
                System.out.println("Puede mover a: "+ cordenada);
            }else if(tablero.getCasilla(cordenada.getRow(), cordenada.getCol()).getPieza().getColor().equals(getColor())){
                System.out.println("Puede comer en: "+cordenada);
            }
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
                for (int i = 0; i < 8; i++) {
                    int rowPos = coordinate.getRow() + (int) Math.round(Math.sin(radians) * ((double) j));
                    int colPos = coordinate.getCol() + (int) Math.round(Math.cos(radians) * ((double) j));
                    radians += Math.toRadians(45.0);

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
        if(coordinate != null){
            int verticalCoordinate = coordinate.getRow();
            int horizantalCoordinate = coordinate.getCol();

            vertical(coordinate, coordinates, verticalCoordinate, horizantalCoordinate);
            horizontal(coordinate, coordinates, verticalCoordinate, horizantalCoordinate);
            diagonalAdelante(coordinate, coordinates, verticalCoordinate, horizantalCoordinate);
            diagonalAtras(coordinate, coordinates, verticalCoordinate, horizantalCoordinate);
        }

        return coordinates;
    }

    private void vertical(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal){

        coordinate.setCol(horizontal);

        for(int i=1; vertical+i <= 7; i++){
            coordinate.setRow(vertical + i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }

        for(int i=1; vertical-i >= 0 ; i++){
            coordinate.setRow(vertical - i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }
    }

    private void horizontal(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal){
        //Parte horizontal
        coordinate.setRow(vertical);

        for(int i=1; horizontal+i <= 7; i++){
            coordinate.setCol(horizontal+i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }
        for(int i=1; horizontal-i >= 0; i++){
            coordinate.setCol(horizontal-i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }
    }

    private void diagonalAdelante(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal){
        //Parte diagonal hacia adelante

        for(int i=1; horizontal+i <= 7 && vertical+i <= 7; i++){
            coordinate.setCol(horizontal+i);
            coordinate.setRow(vertical+i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));

        }

        for(int i=1; horizontal-i >= 0 && vertical+i <= 7; i++){
            coordinate.setCol(horizontal-i);
            coordinate.setRow(vertical+i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }

    }
    private void diagonalAtras(Coordinate coordinate, ArrayList<Coordinate> coordinates, int vertical, int horizontal){
        //Parte diagonal hacia atras
        for(int i=1; horizontal-i >= 0 && vertical-i >= 0; i++){
            coordinate.setCol(horizontal-i);
            coordinate.setRow(vertical-i);
            coordinates.add(new Coordinate(coordinate.getRow(), coordinate.getCol()));
        }

        for(int i=1; horizontal+i <= 7 && vertical-i >= 0 ; i++){
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
            return "\u2655";
        }
        else {
            return "\u265B";
        }
    }
}
