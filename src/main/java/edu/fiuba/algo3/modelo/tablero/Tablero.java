package edu.fiuba.algo3.modelo.tablero;

import java.util.*;

import edu.fiuba.algo3.modelo.DataClassCelda;
import edu.fiuba.algo3.modelo.DataClassTablero;
import edu.fiuba.algo3.modelo.Gladiador;
import edu.fiuba.algo3.modelo.interactuable.Interactuable;
import edu.fiuba.algo3.modelo.interactuable.InteractuableFactory;

public class Tablero {

    private final Celda[][] grillas;
    private final List<Casilla> casillasDelCamino = new ArrayList<>();
    private final Camino camino;

    public Tablero(List<Gladiador> gladiadores, DataClassTablero mapa) {
        this.grillas = new Celda[mapa.largo][mapa.ancho];
        this.camino = new Camino(gladiadores);
        this.crearCaminoEnTablero(mapa);
        this.construirElResto(mapa);
    }

    public void turnoDe(Gladiador g) {
        g.jugarTurno(this.camino);
    }

    private void crearCaminoEnTablero(DataClassTablero mapa) {
        for (DataClassCelda celdaCamino: mapa.camino) {
            Interactuable premio = InteractuableFactory.crearInteractuable(celdaCamino.premio);
            Interactuable obstaculo = InteractuableFactory.crearInteractuable(celdaCamino.obstaculo);
            Casilla casilla = new Casilla(premio, obstaculo);
            this.casillasDelCamino.add(casilla);
            this.camino.agregarCasilla(casilla);
            this.grillas[celdaCamino.x][celdaCamino.y] = casilla;
        }
    }
    public void construirElResto(DataClassTablero mapa) {
        for (int y = 0; y < mapa.ancho; y++) {
            for (int x = 0; x < mapa.largo; x++) {
                if (this.grillas[x][y] == null)
                    this.grillas[x][y] = new CeldaSinCamino();
            }
        }
    }

    public void eliminarGladiador(Gladiador g) {
        this.camino.eliminarGladiadorDeCamino(g);
    }
}
