package co.edu.uniquindio.ajedrez.logic.piezas;

import co.edu.uniquindio.ajedrez.logic.Casilla;
import co.edu.uniquindio.ajedrez.logic.Tablero;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;

public class Torre extends Pieza implements IMover{

    private TipoPieza tipo = TipoPieza.TORRE;

    public Torre(Color color) {
        super(color);
    }

    @Override
    public void mover(Casilla coordinate) {

    }

    public ArrayList<Coordinate> movidas(Pieza pieza) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate = pieza.getCasilla().getCoordinate();
        if (coordinate != null) {
            // Las posibles posiciones del caballo pueden ser obtenidas teniendo en cuenta que las posiciones
            // toman la forma de un circulo espaciado cada 45 grados.
            double radians = Math.toRadians(0.0);
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
            for (int i = 0; i < 8; i++) {
                Coordinate coordenada = new Coordinate (coordinate.getRow(), i);
                if (i != coordinate.getRow()) {
                    coordinates.add(coordenada);
                }
                coordenada = new Coordinate (i, coordinate.getCol());
                if (i != coordinate.getCol()) {
                    coordinates.add(coordenada);
                }
            }
        }
        return coordinates;
    }*/

    public ArrayList<Coordinate> filtradas(Pieza pieza, Tablero tablero){
        return movidas(this);
    }

    /*public ArrayList<Coordinate> filtradas(Pieza pieza, Tablero tablero){
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int dir; int orient;
        for(int m=0; m<4; m++){
            dir = m%2;
            orient = 1-2 * (m/2);
            movidasPosiblesDir(dir, orient, tablero, pieza, coordinates);
        }
        return coordinates;
    }*/


    public void movidasPosiblesDir(int dir, int orient, Tablero tablero, Pieza pieza, ArrayList<Coordinate> coord){
        int i = pieza.getCasilla().getCoordinate().getRow();
        int j = pieza.getCasilla().getCoordinate().getCol();
        int negDir = (dir+1)%2;
        i += dir*orient;
        j += negDir*orient;
        while( i*i <= 7*i && j*j <= 7*j ) {
            if (tablero.getCasilla(i, j).getPieza()  != null) {
                if (tablero.getCasilla(i, j).getPieza().getColor() != pieza.getColor())
                {
                    coord.add(new Coordinate(i, j));
                }
                break;
            }
            else {
                coord.add(new Coordinate(i, j));
                i +=dir*orient;
                j +=negDir*orient;
            }
        }
    }

    public String toString() {
        // https://es.wikipedia.org/wiki/S%C3%ADmbolos_de_ajedrez_en_Unicode
        if (this.getColor().equals(Color.BLANCAS)) {
            return "\u2656";
        }
        else {
            return "\u265C";
        }
    }
}
