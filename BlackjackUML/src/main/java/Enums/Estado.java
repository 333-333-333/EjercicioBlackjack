package Enums;

public enum Estado {
	ACTIVO("Activo"),
	BAJADO("Bajado"),
	CREADO("Creado"),
	GANADOR("Ganador"),
	PERDEDOR("Perdedor");

	private String Estado;

	private Estado(String estado) {
		this.Estado = estado;
	}

	public String getEstado() {
		return this.Estado;
	}

}