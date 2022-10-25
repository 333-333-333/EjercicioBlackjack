package Enums;

public enum Indice {
	AS('A',  11),
	DOS('2',  2), TRES('3',  3), CUATRO('4',  4),
	CINCO('5', 5), SEIS('6', 6), SIETE('7', 7),
	OCHO('8', 8), NUEVE('9', 9), DIEZ('D', 10),
	JOTA('J', 10), QUINA('Q', 10), KAISER('K', 10);

	private char Indice;
	private int Valor;

	private Indice(char indice, int valor) {
		this.Indice = indice;
		this.Valor = valor;
	}

	public int getValor() {
		return this.Valor;

	}

	public char getIndice() {
		return this.Indice;
	}

}