package co.edu.uniquindio.ajedrez.logic.piezas;

import co.edu.uniquindio.ajedrez.logic.Casilla;
import co.edu.uniquindio.ajedrez.logic.Tablero;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;

public abstract class Pieza implements IPieza, IMover {

    private TipoPieza tipo;
    private Color color;
    private Casilla casilla;

    public Pieza(Color color) {
        this.color = color;
    }

    @Override
    public TipoPieza getTipo() {
        return tipo;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public Casilla getCasilla() {
        return casilla;
    }

    public void setCasilla(Casilla casilla) {
        this.casilla = casilla;
    }

    public abstract ArrayList<Coordinate> movidas(Pieza pieza);

    public abstract ArrayList<Coordinate> filtradas(Pieza pieza, Tablero tablero);
}
