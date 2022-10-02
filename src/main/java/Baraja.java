import java.util.ArrayList;
import java.util.Collections;

public class Baraja {
    private ArrayList<Carta> Cartas = new ArrayList<>();
    private int cantidadCartas;

    public Baraja() {
        String[] pintas = {"Diamante", "Pica", "Trébol", "Corazón"};
        String[] indices = {"A", "2", "3", "4", "5", "6", "7",
                            "8", "9", "D", "J", "Q", "K"};

        for (String pinta : pintas) {
            for (int indiceCarta = 1; indiceCarta < indices.length+1; indiceCarta++) {
                if (indiceCarta<10) {
                    this.Cartas.add(new Carta(indices[indiceCarta-1], pinta, indiceCarta));
                } else {
                    this.Cartas.add(new Carta(indices[indiceCarta-1], pinta, 10));
                }
            }
        }
        revolver();
        this.cantidadCartas = this.Cartas.size();
    }

    private void revolver() {
        Collections.shuffle(this.Cartas);
    }

    public Carta darCarta() {
        if (getCantidadCartas()==0) throw new IndexOutOfBoundsException("Baraja vacía.");

        Carta carta = this.Cartas.get(0);
        this.Cartas.remove(0);
        this.cantidadCartas = this.Cartas.size();
        return carta;
    }

    public int getCantidadCartas() {
        return this.cantidadCartas;
    }

    @Override
    public String toString(){
        String stringBaraja = "N° de cartas: "+ this.cantidadCartas +"\n";
        for (Carta carta : this.Cartas) {
            stringBaraja += carta.toString() + "\n";
        }
        return stringBaraja;
    }

}
