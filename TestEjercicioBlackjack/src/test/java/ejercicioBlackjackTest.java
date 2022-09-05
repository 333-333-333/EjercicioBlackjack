import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ejercicioBlackjackTest extends TestCase {

    @Test
    public void testPedirCarta() {
        String[][] mazo = {{"10", null}};
        String[] manoJugador = {"10 de pica"};
        String[] resultadoEsperado = {"10 de pica"};
        assertArrayEquals(ejercicioBlackjack.pedirCarta(mazo,manoJugador,false),resultadoEsperado);

    }

    @Test
    public void testVerificarGanador() {

        String[] manoJugador = {"10 de pica"};
        String[] manoCPU = {"-8 de pica"};
        assertFalse(ejercicioBlackjack.verificarGanador(manoJugador,manoCPU));
    }

    //El método bajarse es un void, no se puede probar. No obstante, se probará uno de los métodos que están dentro de este, el método repartir.
    @Test
    public void testRepartir() {
        String[][] mazoVacío = new String[0][2];
        String[][] resultadoReal = ejercicioBlackjack.repartir(mazoVacío);
        assertArrayEquals(resultadoReal, mazoVacío);
    }





}