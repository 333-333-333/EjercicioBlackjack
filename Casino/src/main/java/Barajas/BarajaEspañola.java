package Barajas;

import Cartas.Carta;
import Enums.Indice;
import Enums.Pinta;

public class BarajaEspañola extends Baraja{

    public BarajaEspañola() {
        super();

        for (Pinta p : Pinta.values()) {
            for (Indice i : Indice.values()) {
                if (p.esEspañola() && i.esEspañola()) {
                    super.getCartas().add(new Carta(i, p));
                }
            }

        }
    }

}
