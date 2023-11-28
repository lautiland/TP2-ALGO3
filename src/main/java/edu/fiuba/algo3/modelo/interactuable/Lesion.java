package edu.fiuba.algo3.modelo.interactuable;

import edu.fiuba.algo3.modelo.Gladiador;

public class Lesion implements Interactuable {
    @Override
    public void interactuarCon(Gladiador g) {
        System.out.print(", te lesionaste");
        g.esLesionado();
    }
}