package edu.fiuba.algo3.testUnitarios;

import edu.fiuba.algo3.model.Dado;
import edu.fiuba.algo3.model.Gladiador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EquipamientoTest {

    private static final int PUNTOS_FINALES_ATAQUE_CON_ESCUDO_ESPADA = 18;
    private static final int PUNTOS_FINALES_ATAQUE_CON_ARMADURA = 10;

    @Test
    public void test01ArmaduraResta10PuntosDeEnergia() {
        //Arrange
        Gladiador gladiador = new Gladiador("Ignacius", new Dado());

        //Act
        gladiador.actualizarEquipamiento(); //obtiene casco
        gladiador.actualizarEquipamiento(); //obtiene armadura
        gladiador.recibirAtaque();

        //Assert, gladiador inicia con 20 puntos
        assertEquals(gladiador.obtenerPuntosEnergia(), PUNTOS_FINALES_ATAQUE_CON_ARMADURA);

    }

    @Test
    public void test02EscudoYEspadaResta2PuntosDeEnergia() {
        //Arrange
        Gladiador gladiador = new Gladiador("Ignacius", new Dado());

        //Act
        gladiador.actualizarEquipamiento(); //obtiene casco
        gladiador.actualizarEquipamiento(); //obtiene armadura
        gladiador.actualizarEquipamiento(); //escudo y espada
        gladiador.recibirAtaque();

        //Assert, gladiador inicia con 20 puntos
        assertEquals(gladiador.obtenerPuntosEnergia(), PUNTOS_FINALES_ATAQUE_CON_ESCUDO_ESPADA);
    }
}
