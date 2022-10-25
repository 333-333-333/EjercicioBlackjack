package Objetos;

import Enums.Indice;
import Enums.Pinta;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ManoTest {

    private Mano Test;
    private Logger Logger;

    @BeforeEach
    void init() {
        this.Test = new Mano();
        this.Logger = Logger.getLogger("Mano.class");
    }

    @BeforeAll
    static void limpiarLog() {
        try {
            new File("testinglogs.log").delete();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("[agregarCarta] Comprueba la fidelidad del método.")
    @Test
    void robarCartaFidelidad() {
        Carta c = new Carta(Indice.AS, Pinta.CORAZON);
        this.Test.agregarCarta(c);

        assertEquals(1,
                this.Test.obtenerCantidadCartas(),
                "La cantidad de cartas no coincide.");

        Logger.info("[agregarCarta]" +
                " La cantidad de cartas no coincide.");
    }

    @DisplayName("[robarCarta] Asegura descuento de carta de la baraja.")
    @Test
    void robarCartaDescuentaBaraja() {
        Baraja b = new Baraja();
        this.Test.robarCarta(b);

        assertEquals(51,
                b.getCantidadCartas(),
                "No se descontó la carta de la baraja.");

        Logger.info("[robarCarta]"
                + " No se descontó la carta de la baraja.");
    }

    @DisplayName("[obtenerValor] Verifica la integridad del método.")
    @Test
    void obtenerValorFidelidad() {
        Carta c = new Carta(Indice.AS, Pinta.CORAZON);
        Carta c2 = new Carta(Indice.QUINA, Pinta.DIAMANTE);

        this.Test.agregarCarta(c);
        this.Test.agregarCarta(c2);

        assertEquals(21,
                this.Test.obtenerValor(),
                "El valor no coincide");

        Logger.info("[obtenerValor]"
                + " El valor no coincide");
    }

    @DisplayName("[obtenerCantidadCartas] Comprueba la fidelidad del método.")
    @Test
    void obtenerCantidadCartasFidelidad() {
        Carta c = new Carta(Indice.AS, Pinta.CORAZON);
        Carta c2 = new Carta(Indice.QUINA, Pinta.DIAMANTE);

        this.Test.agregarCarta(c);
        this.Test.agregarCarta(c2);

        assertEquals(2,
                this.Test.obtenerCantidadCartas(),
                "La cantidad de cartas no coincide.");

        Logger.info("[obtenerCantidadCartas]"
                + " La cantidad de cartas no coincide.");
    }

    @DisplayName("[contieneAs] Comprueba la fidelidad del método.")
    @Test
    void contieneAsFidelidad() {
        Carta c = new Carta(Indice.AS, Pinta.CORAZON);
        Carta c2 = new Carta(Indice.QUINA, Pinta.DIAMANTE);

        this.Test.agregarCarta(c);
        this.Test.agregarCarta(c2);

        assertTrue(Test.contieneAs(),
                "No detecta el As.");

        Logger.info("[contieneAs]"
                + " No detecta el As.");
    }

    @DisplayName("[contieneAs] Comprueba la fidelidad del método.")
    @Test
    void contieneAsFidelidadSinAs() {
        Carta c = new Carta(Indice.JOTA, Pinta.CORAZON);
        Carta c2 = new Carta(Indice.QUINA, Pinta.DIAMANTE);

        this.Test.agregarCarta(c);
        this.Test.agregarCarta(c2);

        assertFalse(Test.contieneAs(),
                "Detecta un As inexistente");

        Logger.info("[contieneAs]"
                + " Detecta un As inexistente.");
    }

}