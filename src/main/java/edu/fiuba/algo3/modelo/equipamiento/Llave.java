package edu.fiuba.algo3.modelo.equipamiento;

import edu.fiuba.algo3.modelo.tablero.Camino;
import edu.fiuba.algo3.modelo.Gladiador;

public class Llave extends Equipamiento {
    @Override
    public void resistirAtaque(Gladiador gladiador) {
        System.out.print(", resististe todo el ataque");
        gladiador.modificarEnergia(0);
    }

    @Override
    public Equipamiento actualizar() {
        System.out.print(", tenes todo el equipamiento, no recibis nada");
        return this;
    }

    @Override
    public boolean abrirPuerta (Gladiador gladiador, Camino camino) {
        System.out.print("\n"+ gladiador.NOMBRE + " GANASTE!!!, la casa al pie del monte Vesubio es tuya!!!!");
        return true;
    }

}
