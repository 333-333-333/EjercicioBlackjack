import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {

    private Juego testJuego;
    private Jugador testJugador;
    private Jugador testDealer;
    private Carta testCarta;
    private Baraja testBaraja;
    private Mano testMano;
    private Mano testManoVacia;

    @BeforeEach
    void init() {
        this.testJuego = new Juego(100000);
        this.testJugador = new Jugador(100000, "Test Jugador");
        this.testDealer = new Jugador(100000, "Test Dealer");
        this.testBaraja = new Baraja();
        this.testManoVacia = new Mano();
    }

    @AfterEach
    void tear() {
        this.testJuego = null;
        this.testJugador = null;
        this.testDealer = null;
        this.testBaraja = null;
        this.testCarta = null;
        this.testMano = null;
    }

    // Test para clase Mano.

    @DisplayName("[Mano] ObtenerCarta, aumenta tamaño del arreglo de cartas.")
    @Test
    void testObtenerCarta_agregaCarta() {
        this.testMano = new Mano(this.testBaraja);
        this.testMano.obtenerCarta(this.testBaraja);
        assertEquals(3, this.testMano.getCantidadCartas());
    }

    @DisplayName("[Mano] agregarCarta, aumenta tamaño del arreglo de cartas.")
    @Test
    void testAgregarCarta_indiceAs() {
        this.testMano = new Mano(this.testBaraja);
        this.testCarta = new Carta("A", "Trébol", 11);
        this.testMano.agregarCarta(this.testCarta);
        assertEquals(3, this.testMano.getCantidadCartas());
    }

    @DisplayName("[Mano] contieneIndice, comprueba indice en arreglo de cartas.")
    @Test
    void testContieneIndice_indiceAs() {
        this.testMano = new Mano(this.testBaraja);
        this.testCarta = new Carta("A", "Trébol", 11);
        this.testMano.agregarCarta(this.testCarta);
        assertTrue(this.testMano.contieneIndice("A"));
    }

    @DisplayName("[Mano] contieneValor, comprueba valor en arreglo de cartas.")
    @Test
    void testContieneValor_11() {
        this.testMano = new Mano(this.testBaraja);
        this.testCarta = new Carta("A", "Trébol", 11);
        this.testMano.agregarCarta(this.testCarta);
        assertTrue(this.testMano.contieneValor(11));
    }

    // La clase carta no contiene los suficientes métodos para el testing.

    // Test clase Jugador.

    @DisplayName("[Jugador] limpiarMano, se actualiza la mano a null.")
    @Test
    void testLimpiarMano_fidelidad() {
        this.testJugador.limpiarMano();
        assertNull(this.testJugador.getMano());
    }

    @DisplayName("[Jugador] valorarMano, comprobar fidelidad a valor correcto.")
    @Test
    void testValorarMano_fidelidad() {
        Carta carta1 = new Carta("J", "Trébol", 10);
        Carta carta2 = new Carta("8", "Trébol", 8);
        this.testManoVacia.agregarCarta(carta1);
        this.testManoVacia.agregarCarta(carta2);
        this.testDealer.setMano(this.testManoVacia);
        this.testDealer.valorarMano();
        assertEquals(18, this.testDealer.getValorMano());
    }

    // Test clase Baraja

    @DisplayName("[Baraja] darCarta, se decrementa el tamaño de la baraja.")
    @Test
    void testDarCarta_decrementaCantidadCartas() {
        Carta test = this.testBaraja.darCarta();
        assertEquals(51, this.testBaraja.getCantidadCartas());
    }

}