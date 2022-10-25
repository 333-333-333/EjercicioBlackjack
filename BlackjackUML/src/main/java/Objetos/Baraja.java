package Objetos;

import Enums.Indice;
import Enums.Pinta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {

	private List<Carta> Cartas;

	public Baraja() {
		this.Cartas = new ArrayList<>();
		for (Pinta pinta : Pinta.values()) {
			for (Indice indice : Indice.values()) {
				Carta carta = new Carta(indice, pinta);
				this.Cartas.add(carta);
			}
		}
	}

	public void barajar() {
		if (this.Cartas.size() == 0)  {
			throw new ArrayIndexOutOfBoundsException("No hay cartas.");
		}
		Collections.shuffle(this.Cartas);
	}

	public Carta darCarta() {
		if (this.Cartas.size() == 0)  {
			throw new ArrayIndexOutOfBoundsException("No hay cartas.");
		}
		return this.Cartas.remove(0);
	}

	public List<Carta> getCartas() {
		return this.Cartas;
	}

	public int getCantidadCartas() {
		return this.Cartas.size();
	}

}