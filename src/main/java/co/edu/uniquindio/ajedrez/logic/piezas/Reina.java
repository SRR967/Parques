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
    }

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
