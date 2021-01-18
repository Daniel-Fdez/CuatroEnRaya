package org.iesalandalus.programacion.cuatroenraya.modelo;

public class Jugador {
	private String nombre;
	private Ficha colorFichas;
	
	// Constructor para establecer el nombre del jugador y el color de las fichas
	public Jugador(String nombre, Ficha colorFichas) {
		setNombre(nombre);
		setColorFichas(colorFichas);
	}
	
	// Devuelve el nombre del jugador
	public String getNombre() {
		return nombre;
	}
	
	// Establece el nombre del jugador y comprueba errores
	private void setNombre(String nombre) {
		if(nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		}
		if(nombre.equals("") || nombre.matches("\\s+")) {
			throw new IllegalArgumentException("ERROR: El nombre no puede estar vacío.");
		}
		this.nombre = nombre;
	}
	
	// Devuelve el color de las fichas
	public Ficha getColorFichas() {
		return colorFichas;
	}
	
	// Establece el color de las fichas y comprueba que no se pase un valor nulo
	private void setColorFichas(Ficha colorFichas) {
		if(colorFichas == null) {
			throw new NullPointerException("ERROR: El color de las fichas no puede ser nulo.");
		}
		this.colorFichas = colorFichas;
	}

	// Método toString para mostrar el nombre del jugador y el color de las fichas
	@Override
	public String toString() {
		return nombre + " (" + colorFichas + ")";
	}
}
