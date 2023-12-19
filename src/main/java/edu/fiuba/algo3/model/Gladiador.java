package edu.fiuba.algo3.model;

import edu.fiuba.algo3.model.equipamiento.Equipamiento;
import edu.fiuba.algo3.model.equipamiento.SinEquipamiento;
import edu.fiuba.algo3.model.estado.Estado;
import edu.fiuba.algo3.model.estado.Lesionado;
import edu.fiuba.algo3.model.estado.Sano;
import edu.fiuba.algo3.model.seniority.Novato;
import edu.fiuba.algo3.model.seniority.Seniority;
import edu.fiuba.algo3.model.tablero.Camino;
import edu.fiuba.algo3.view.newView.ObserverEquipamiento;

public class Gladiador{

    private final String NOMBRE;
    private final Energia ENERGIA = new Energia();
    private final Tiradas TIRADAS;
    private Estado estado = new Sano();
    private Seniority seniority = new Novato();
    private Equipamiento equipamiento = new SinEquipamiento();
    private final ObserverEquipamiento OBSERVER_EQUIPAMIENTO;

    public Gladiador(String nombre, Dado dado, ObserverEquipamiento observerEquipamiento) {
        this.TIRADAS = new Tiradas(dado);
        this.NOMBRE = nombre;
        this.OBSERVER_EQUIPAMIENTO = observerEquipamiento;
    }


    public void jugarTurno(Camino camino) {
        Logger.getInstance().info("Es el turno de " + this.NOMBRE);
        this.estado = this.estado.jugar(this, camino);
        this.TIRADAS.verificarLimite(this, camino);
    }

    public void actualizarEquipamiento() {
        this.equipamiento = this.equipamiento.actualizar();
        OBSERVER_EQUIPAMIENTO.actualizar(this.equipamiento);
    }

    public void actualizarSeniority() {
        this.seniority = this.seniority.actualizar(this);
    }

    public void recibirAtaque() {
        this.equipamiento.resistirAtaque(this);
    }

    public void modificarEnergia(int puntos) {
        this.ENERGIA.modificarPuntos(puntos);
    }

    public void lesionar() {
        this.estado = new Lesionado();
    }

    public int calcularMovimientos() {
        return this.TIRADAS.tirarDado();
    }

    public Estado actualizarEstado() {
        return this.ENERGIA.actualizarEstado(this.estado);
    }

    public String getNombre() {
        return this.NOMBRE;
    }

    public int obtenerPuntosEnergia() {
        return this.ENERGIA.obtenerPuntos();
    }

    public boolean abrirPuerta(Camino camino) {
        return this.equipamiento.abrirPuerta(this, camino);
    }
}
