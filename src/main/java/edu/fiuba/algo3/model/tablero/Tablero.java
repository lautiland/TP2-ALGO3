package edu.fiuba.algo3.model.tablero;

import edu.fiuba.algo3.model.Gladiador;
import edu.fiuba.algo3.model.interactuable.Interactuable;
import edu.fiuba.algo3.model.interactuable.InteractuableFactory;
import edu.fiuba.algo3.model.parser.DataClassCelda;
import edu.fiuba.algo3.model.parser.DataClassTablero;

import java.util.List;

public class Tablero {
    private final Celda[][] grilla;
    private final Camino camino;
    private DataClassCelda salida;
    private DataClassTablero mapa;

    public Tablero(List<Gladiador> gladiadores, DataClassTablero mapa) {
        this.mapa = mapa;
        this.grilla = new Celda[this.mapa.LARGO][this.mapa.ANCHO];
        this.camino = new Camino(gladiadores);
        this.salida = mapa.getCamino().get(0);
        this.crearCaminoEnTablero(this.mapa);
        this.construirElResto(this.mapa);
    }

    public void turnoDe(Gladiador gladiador) {
        gladiador.jugarTurno(this.camino);
    }

    public DataClassCelda obtenerPosicionDe(Gladiador gladiador) {
        int casillasAvanzadas = this.camino.obtenerPosicionDe(gladiador);
        return mapa.getCamino().get(casillasAvanzadas);
    }

    private void crearCaminoEnTablero(DataClassTablero mapa) {
        for (DataClassCelda celdaCamino : mapa.getCamino()) {
            Interactuable premio = InteractuableFactory.crearInteractuable(celdaCamino.premio);
            Interactuable obstaculo = InteractuableFactory.crearInteractuable(celdaCamino.obstaculo);
            Casilla casilla = new Casilla(premio, obstaculo);
            this.camino.agregarCasilla(casilla);
            this.grilla[celdaCamino.X][celdaCamino.Y] = casilla;
        }
        DataClassCelda celdaLlegada = mapa.getLlegada();
        Interactuable premioLlegada = InteractuableFactory.crearInteractuable("Llegada");
        Interactuable obstaculoLlegada = InteractuableFactory.crearInteractuable(celdaLlegada.obstaculo);
        Casilla casillaLlegada = new Casilla(premioLlegada, obstaculoLlegada);
        this.camino.agregarCasilla(casillaLlegada);
        this.grilla[celdaLlegada.X][celdaLlegada.Y] = casillaLlegada;
    }

    private void construirElResto(DataClassTablero mapa) {
        for (int y = 0; y < mapa.ANCHO; y++) {
            for (int x = 0; x < mapa.LARGO; x++) {
                if (this.grilla[x][y] == null)
                    this.grilla[x][y] = new CeldaSinCasilla();
            }
        }
    }

    public boolean tieneGanador() {
        return this.camino.tieneGanador();
    }

    public Gladiador obtenerGanador() {
        return this.camino.obtenerGanador();
    }

    public Celda[][] obtenerGrilla() {
        return this.grilla;
    }

    public DataClassCelda obtenerSalida() {
        return this.salida;
    }
}
