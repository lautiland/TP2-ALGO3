package edu.fiuba.algo3.modelo.equipamiento;

import edu.fiuba.algo3.modelo.Energia;
import edu.fiuba.algo3.modelo.Gladiador;

public class Casco extends Equipo {

    @Override
    public void resistirAtaque(Gladiador g) {
        System.out.print(", perdes 15 puntos de energia");
        g.modificarEnergia(-15);
    }

    @Override
    public Equipo actualizar() {
        System.out.print(", recibiste armadura");
        return new Armadura();
    }

}
