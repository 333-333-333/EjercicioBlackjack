package Objetos;

import Enums.Indice;
import Enums.Pinta;
import org.apache.log4j.*;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {

    private Jugador Test;
    private Logger Logger;

    @BeforeEach
    void init() {
        this.Test = new Jugador(909999, "TEST");
        this.Logger = Logger.getLogger("Jugador.class");
    }

    @BeforeAll
    static void limpiarLog() {
        try {
            new File("testinglogs.log").delete();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @DisplayName("[generarMano] Comprueba la fidelidad del método.")
    @Test
    void generarManoFidelidad() {
        Baraja b = new Baraja();
        this.Test.generarMano(b);

        assertEquals(2, this.Test.getCantidadCartas());
        Logger.info("[generarMano]"
                + " No se generó la mano.");
    }

    @DisplayName("[pedirCarta] Comprueba la fidelidad del método.")
    @Test
    void pedirCartaFidelidad() {
        Baraja b = new Baraja();
        this.Test.pedirCarta(b);

        assertEquals(1, this.Test.getCantidadCartas());
        Logger.info("[pedirCarta]"
                + " No se agregó a carta.");
    }

    @DisplayName("[tieneBlackjack] Comprueba la fidelidad del método.")
    @Test
    void tieneBlackjackFidelidad() {
        Mano m = new Mano();
        m.agregarCarta(new Carta(Indice.JOTA, Pinta.CORAZON));
        m.agregarCarta(new Carta(Indice.AS, Pinta.DIAMANTE));

        this.Test.setMano(m);
        assertTrue(this.Test.tieneBlackjack());
        Logger.info("[tieneBlackjack]"
                + " No se detecta el blackjack.");
    }

    @DisplayName("[valorarMano] Comprueba la fidelidad del método.")
    @Test
    void valorarManoTest() {
        Mano m = new Mano();
        m.agregarCarta(new Carta(Indice.JOTA, Pinta.CORAZON));
        m.agregarCarta(new Carta(Indice.AS, Pinta.DIAMANTE));

        this.Test.setMano(m);
        assertEquals(21, this.Test.valorarMano());
        Logger.info("[valorarMano]"
                + " No arroja el valor correcto.");
    }
}