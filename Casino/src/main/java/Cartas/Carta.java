package Cartas;
import Enums.*;

public class Carta {
    private Pinta Pinta;
    private Indice Indice;

    public Carta(Indice indice, Pinta pinta) {
        this.Indice = indice;
        this.Pinta = pinta;
    }

    public Pinta getPinta() {
        return this.Pinta;
    }

    public Indice getIndice() {
        return this.Indice;
    }

    public void setPinta(Enums.Pinta pinta) {
        Pinta = pinta;
    }

    public void setIndice(Enums.Indice indice) {
        Indice = indice;
    }

    @Override
    public String toString() {
        return this.Indice + " DE " + this.Pinta;
    }
}
