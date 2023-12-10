package edu.fiuba.algo3.model.tablero;

import edu.fiuba.algo3.model.Gladiador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Camino {

    private final List<Casilla> sendero = new ArrayList<>();
    private final Map<Gladiador, Integer> gladiadoresANumeroDeCasilla = new HashMap<>();
    private boolean premioReclamado = false;
    private Gladiador ganador;

    public Camino(List<Gladiador> gladiadores) {
        for (Gladiador gladiador : gladiadores) {
            gladiadoresANumeroDeCasilla.put(gladiador, 0);
        }
    }

    public void agregarCasilla(Casilla camino) {
        this.sendero.add(camino);
    }

    public void mover(Gladiador gladiador, int numeroCasillas) {
        int numeroDeCasillaActualDelGladiador = this.gladiadoresANumeroDeCasilla.get(gladiador);
        int nuevaUbicacion = numeroDeCasillaActualDelGladiador + numeroCasillas;
        if (nuevaUbicacion > this.sendero.size()) {
            nuevaUbicacion = this.sendero.size();
        }
        this.desplazarGladiadorEInteractuar(gladiador, nuevaUbicacion);
    }

    public boolean estaEl(Gladiador gladiador, int enCasillaNumero) {
        return this.gladiadoresANumeroDeCasilla.get(gladiador) == enCasillaNumero;
    }

    private void desplazarGladiadorEInteractuar(Gladiador gladiador, int nuevaUbicacion) {
        Casilla casilla;
        try {
            casilla = this.sendero.get(nuevaUbicacion);
        } catch (IndexOutOfBoundsException e) {
            casilla = this.sendero.get(this.sendero.size() - 1);
        }
        gladiadoresANumeroDeCasilla.put(gladiador, nuevaUbicacion);
        casilla.interactuarCon(gladiador, this);

    }

    public int obtenerPosicionDe(Gladiador gladiador) {
        return this.gladiadoresANumeroDeCasilla.get(gladiador);
    }

    public void moverAMitad(Gladiador gladiador) {
        this.desplazarGladiadorEInteractuar(gladiador, this.sendero.size() / 2);
    }

    public void eliminarGladiadorDeCamino(Gladiador gladiador) {
        this.gladiadoresANumeroDeCasilla.remove(gladiador);
    }

    public void reclamarPremio(Gladiador gladiador) {
        this.premioReclamado = true;
        this.ganador = gladiador;
    }

    public boolean tieneGanador() {
        return this.premioReclamado;
    }

    public Gladiador obtenerGanador() {
        return this.ganador;
    }
}

