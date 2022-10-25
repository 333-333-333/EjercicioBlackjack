package Enums;

public enum Pinta {
	CORAZON("Corazón"),
	DIAMANTE("Diamante"),
	PICA("Pica"),
	TRÉBOL("Trébol") ;

	private final String Pinta;

	Pinta(String pinta) {
		this.Pinta = pinta;
	}

	public String getPinta() {
		return this.Pinta;
	}

}