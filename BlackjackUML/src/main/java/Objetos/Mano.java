package Objetos;

import java.util.List;

public class Mano {

	private List<Carta> Cartas;

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

	public void robarCarta(Baraja baraja) {
		this.Cartas.add(baraja.darCarta());
	}

	public int obtenerCantidadCartas() {
		return this.Cartas.size();
	}

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