package co.edu.uniquindio.ajedrez.logic.piezas;

import co.edu.uniquindio.ajedrez.logic.Casilla;
import co.edu.uniquindio.ajedrez.logic.Tablero;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;

public class Dummy extends Pieza implements IMover{

    private TipoPieza tipo = TipoPieza.DUMMY;

    public Dummy(Color color) {
        super(color);
    }

    @Override
    public void mover(Casilla coordinate) {

    }

    public ArrayList<Coordinate> movidas(Pieza pieza) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate = pieza.getCasilla().getCoordinate();
        if (coordinate != null) {
            if (pieza.getColor().equals(Color.NEGRAS) && coordinate.getRow() < 7) {
                coordinate.setRow(coordinate.getRow() + 1);
                coordinates.add(coordinate);
            }
            else if (pieza.getColor().equals(Color.BLANCAS) && coordinate.getRow() > 0) {
                coordinate.setRow(coordinate.getRow() - 1);
                coordinates.add(coordinate);
            }

        }
        return coordinates;
    }

    public ArrayList<Coordinate> filtradas(Pieza pieza, Tablero tablero){
        return new ArrayList<>();
    }

    public String toString() {
        // https://es.wikipedia.org/wiki/S%C3%ADmbolos_de_ajedrez_en_Unicode
        if (this.getColor().equals(Color.BLANCAS)) {
            return "\u2659";
        }
        else {
            return "\u265F";
        }
    }
}
