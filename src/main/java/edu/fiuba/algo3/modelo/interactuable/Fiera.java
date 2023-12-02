package edu.fiuba.algo3.modelo.interactuable;

import edu.fiuba.algo3.modelo.Gladiador;
import edu.fiuba.algo3.modelo.tablero.Camino;

public class Fiera implements Interactuable {

    @Override
    public void interactuarCon(Gladiador gladiador, Camino camino) {
        System.out.print(", caes en la jaula del leon");
        gladiador.recibirAtaque();
    }
}
