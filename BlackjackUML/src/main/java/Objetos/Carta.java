package Objetos;

import Enums.*;

public class Carta {

	private Enums.Indice Indice;
	private Enums.Pinta Pinta;

	public Carta (Indice indice, Pinta pinta) {
		this.Indice = indice;
		this.Pinta = pinta;
	}

	public char getIndice() {
		return this.Indice.getIndice();
	}

	public int getValor() {
		return this.Indice.getValor();
	}

	@Override
	public String toString() {
		return this.Indice.getIndice() + " de " + this.Pinta.getPinta();
	}

}