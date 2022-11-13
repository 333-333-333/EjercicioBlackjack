package Barajas;


import Cartas.Carta;
import Enums.Indice;
import Enums.Pinta;

public class BarajaInglesa extends Baraja{

    public BarajaInglesa(boolean tieneJoker) {
        super();

        for (Pinta p : Pinta.values()) {
            for (Indice i : Indice.values()) {
                if (p.esInglesa() && i.esInglesa()) {
                    if (i != Indice.JOKER) {
                        super.getCartas().add(new Carta(i, p));
                    } else if (tieneJoker) {
                            super.getCartas().add(new Carta(i, p));
                    }
                }
            }
        }

    }


}
