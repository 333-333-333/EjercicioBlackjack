package Barajas;

import Cartas.Carta;
import Enums.*;

import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    private ArrayList<Carta> Cartas;

    public Baraja() {
        this.Cartas = new ArrayList<>();
    }

    public void barajar() {
        Collections.shuffle(this.Cartas);
    }

    public Carta darCarta() {
        if (this.Cartas.size() == 0) {
            throw new NullPointerException("No hay cartas");
        }
        return this.Cartas.remove(0);
    }

    public ArrayList<Carta> getCartas() {
        return Cartas;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        Cartas = cartas;
    }

}
