package co.edu.uniquindio.ajedrez.logic.piezas;

import co.edu.uniquindio.ajedrez.logic.Casilla;
import co.edu.uniquindio.ajedrez.logic.Tablero;
import co.edu.uniquindio.ajedrez.logic.util.Coordinate;

import java.util.ArrayList;
import java.util.Scanner;

public class Peon extends Pieza implements IMover{

    private TipoPieza tipo = TipoPieza.PEON;

    public Peon(Color color) {
        super(color);
    }

    public ArrayList<Coordinate> movidas(Pieza pieza) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        Coordinate coordinate = pieza.getCasilla().getCoordinate();
        boolean isFirstMove = true;

        if (pieza.getColor().equals(Color.NEGRAS) && coordinate != null) {

            if ( coordinate.getRow() < 7 ) {
                Coordinate coord1 = new Coordinate(coordinate.getRow() + 1, coordinate.getCol());
                coordinates.add(coord1);
            }

            if(isFirstMove &&  coordinate.getRow() < 7){
                isFirstMove = false;
                Coordinate coord2 = new Coordinate(coordinate.getRow() + 2, coordinate.getCol());
                coordinates.add(coord2);
            }

            if(coordinate.getCol() < 7){
                Coordinate coord3 = new Coordinate(coordinate.getRow() + 1, coordinate.getCol() + 1);
                coordinates.add(coord3);
            }

            if(coordinate.getCol() > 0){
                Coordinate coord4 = new Coordinate(coordinate.getRow() + 1, coordinate.getCol() - 1);
                coordinates.add(coord4);
            }
        }

        else if (pieza.getColor().equals(Color.BLANCAS) && coordinate.getRow() > 0) {

            if ( coordinate.getRow() <= 7) {
                Coordinate coord1 = new Coordinate(coordinate.getRow() - 1, coordinate.getCol());
                coordinates.add(coord1);
            }

            if(isFirstMove &&  coordinate.getRow() < 7){
                isFirstMove = false;
                Coordinate coord2 = new Coordinate(coordinate.getRow() - 2, coordinate.getCol());
                coordinates.add(coord2);
            }

            if(coordinate.getCol() > 0){
                Coordinate coord3 = new Coordinate(coordinate.getRow() - 1, coordinate.getCol() - 1);
                coordinates.add(coord3);
            }

            if(coordinate.getCol() < 7){
                Coordinate coord4 = new Coordinate(coordinate.getRow() - 1, coordinate.getCol() + 1);
                coordinates.add(coord4);
            }
        }

        return coordinates;
    }

    public ArrayList<Coordinate> filtradas(Pieza pieza, Tablero tablero){
        return movidas(this);
    }

    public Pieza reclamar(Color color, int option){
        Pieza piezaReclamada = null;
        ArrayList<Pieza> piezas = new ArrayList<>();

        piezas.add(new Alfil(color));  // 0
        piezas.add(new Caballo(color));// 1
        piezas.add(new Reina(color));  // 2
        piezas.add(new Torre(color));  // 3

        System.out.println(piezas.get(option).toString());

        return piezas.get(option);
    }

    public void validarReclamar(Casilla casilla){
        Tablero tablero = this.getCasilla().getTablero();

        Scanner scanner = new Scanner(System.in);
        int option;

        if(this.getColor() == Color.NEGRAS){
            if(casilla.getCoordinate().getRow() == 7){
                System.out.println("[0] Alfil | [1] Caballo | [2] Reina | [3] Torre");
                option = scanner.nextInt();
                reclamar(Color.NEGRAS, option);
            }
        }
        else{
            if(casilla.getCoordinate().getRow() == 0){
                System.out.println("[0] Alfil | [1] Caballo | [2] Reina | [3] Torre");
                option = scanner.nextInt();
                reclamar(Color.BLANCAS, option);
            }
        }
    }

    /*
     * Imprime las movidas totales y las movidas válidas, ya que la función "movidas" es llamada dentro de esta
     * Se hizo asi, buscando seguir con lo establecido por la firma de la función, la cual pide una casilla y no retorna
     * nada, sin embargo esto indica que no guarda los posibles movimientos una vez terminada su ejecución.
     * */
    @Override
    public void mover(Casilla coordinate) {

        Tablero tablero = this.getCasilla().getTablero(); //Base del código para obtener el tablero

        Coordinate coord0 = coordinate.getCoordinate(); //Suponemos que esta es la casilla donde está el peon

        ArrayList<Coordinate> movidasValidas = new ArrayList<>();
        ArrayList<Coordinate> movidasTotales = movidas(this);

        for(int i = 0; i < movidasTotales.size(); i ++){
            //Solo hace comprobaciones
            Casilla casillaAux = tablero.getCasilla(movidasTotales.get(i).getRow(), movidasTotales.get(i).getCol());

            /*
             * Termina siendo redundante a partir del segundo turno, ya que comprueba dos veces la misma casilla, pero
             * es la única forma que se encontró para que funcione para el doble movimiento del inicio de turno.
             */
            if(casillaAux.getCoordinate().getCol() == coord0.getCol()){ //Movimiento avanzar
                if(this.getColor() == Color.NEGRAS){ //Negras bajan
                    if(casillaAux.getCoordinate().getRow() == coord0.getRow() + 1){
                        if(casillaAux.getPieza() == null){
                            movidasValidas.add(movidasTotales.get(i));
                        }
                    }
                    else{ //Doble casilla
                        if(casillaAux.getPieza() == null &&
                                tablero.getCasilla(coord0.getRow()+ 1, coord0.getCol()).getPieza() == null){
                            movidasValidas.add(movidasTotales.get(i));
                        }
                    }
                }
                else{ //Blancas suben
                    if(casillaAux.getCoordinate().getRow() == coord0.getRow() - 1){
                        if(casillaAux.getPieza() == null){
                            movidasValidas.add(movidasTotales.get(i));
                        }
                    }
                    else{ //Doble casilla
                        if(casillaAux.getPieza() == null &&
                                tablero.getCasilla(coord0.getRow()- 1, coord0.getCol()).getPieza() == null){
                            movidasValidas.add(movidasTotales.get(i));
                        }
                    }
                }
            }
            else if(casillaAux.getCoordinate().getCol() != coord0.getCol() && casillaAux.getPieza() != null){
                movidasValidas.add(movidasTotales.get(i));
            }
        }
        System.out.println("Todas las movidas: " + movidasTotales + "\n" +
                "Movidas Validas : " + movidasValidas);
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
