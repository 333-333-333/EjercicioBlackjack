package Objetos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import Enums.Indice;
import Enums.Pinta;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BarajaTest {

    private Baraja Test;
    private Logger Logger;

    @BeforeEach
    void init() {
        this.Test = new Baraja();
        this.Logger = Logger.getLogger("Baraja.class");
    }

    @BeforeAll
    static void limpiarLog() {
        try {
            new File("testinglogs.log").delete();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("[barajar] Comprueba la fidelidad del método.")
    @Test
    void barajarFidelidad() {
        List<Carta> original = this.Test.getCartas();
        this.Test.barajar();
        List<Carta> nueva = this.Test.getCartas();

        assertTrue(original.equals(nueva), "Las listas son iguales.");
        Logger.info("[barajar]"
                + " Las barajas son iguales.");
    }

    @DisplayName("[darCarta] Comprueba la fidelidad del método.")
    @Test
    void darCartaFidelidad() {
        Mano m = new Mano();
        m.robarCarta(this.Test);

        assertEquals(51, this.Test.getCantidadCartas());
        Logger.info("[darCarta]"
            + " El tamaño de la baraja no se modifica.");
    }
}