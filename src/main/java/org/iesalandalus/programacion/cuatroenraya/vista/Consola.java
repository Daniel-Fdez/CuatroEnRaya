package org.iesalandalus.programacion.cuatroenraya.vista;

import org.iesalandalus.programacion.cuatroenraya.modelo.Ficha;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {
		
	}
	
	public static void elegirColorFichas(char ficha) {
		char eleccion;
		do {
			eleccion = Entrada.caracter();
			if(eleccion == 'A') {
				
			}
		} while(ficha != 'A' || ficha != 'V');
	}
}
