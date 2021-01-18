package org.iesalandalus.programacion.cuatroenraya.modelo;

import javax.naming.OperationNotSupportedException;

public class Casilla {
	Ficha ficha;
	
	// Constructor por defecto que le da valor "null" al atributo ficha
	public Casilla() {
		this.ficha = null;
	}
	
	// Método que devuelve la ficha
	public Ficha getFicha() {
		return ficha;
	}

	/* Comprueba si el valor de la ficha es correcto y comprueba si hay una ficha 
	   en el lugar donde se quiere poner para establecer el color de la ficha */
	public void setFicha(Ficha ficha) throws OperationNotSupportedException {
		if(estaOcupada() == false) {
			if(ficha == null) {
				throw new NullPointerException("ERROR: No se puede poner una ficha nula.");
			} else if(ficha == Ficha.AZUL) {
				this.ficha = Ficha.AZUL;
			} else if(ficha == Ficha.VERDE) {
				this.ficha = Ficha.VERDE;
			}
		} else {
			throw new OperationNotSupportedException("ERROR: Ya contengo una ficha.");
		}
	}

	// Comprueba si la casilla tiene una ficha o no
	public boolean estaOcupada() {
		return this.ficha != null;
	}

	// Muestra la inicial del valor de la ficha o un espacio en blanco si no se encuentra ocupada la casilla
	@Override
	public String toString() {
		if(ficha == null) {
			return " ";
		} else {
			return String.format("%.1s", ficha);
		}
	}
}
