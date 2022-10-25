package Objetos;

import java.util.ArrayList;
import java.util.List;

public class Mano {

	private List<Carta> Cartas;

	public Mano() {
		this.Cartas = new ArrayList<>();
	}

	// Testeado
	public int obtenerValor() {
		if (this.Cartas.size() == 0) {
			throw new ArrayIndexOutOfBoundsException("La mano está vacía.");
		}
		int valorMano = 0;
		for (Carta carta : this.Cartas) {
			valorMano += carta.getValor();
			if (carta.getIndice() == 'A' && valorMano > 21) {
				valorMano -= 10;
			}
		}
		return valorMano;
	}

	// Testeado
	public void robarCarta(Baraja baraja) {
		agregarCarta(baraja.darCarta());
	}

	// Testeado
	public void agregarCarta(Carta carta){
		this.Cartas.add(carta);
	}

	// Testeado
	public int obtenerCantidadCartas() {
		return this.Cartas.size();
	}

	// Testeado
	public boolean contieneAs() {
		if (this.Cartas.size() == 0) {
			throw new ArrayIndexOutOfBoundsException("La mano está vacía.");
		}
		for (Carta carta : this.Cartas) {
			if (carta.getIndice() == 'A') {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String manoToString = "";
		for (Carta carta : this.Cartas) {
			manoToString += carta.toString() + "\n";
		}
		return manoToString;
	}

}