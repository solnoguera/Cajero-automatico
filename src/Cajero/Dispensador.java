package Cajero;

import java.util.HashMap;
import java.util.Map;

public class Dispensador {

	private Map<Integer, Integer> cantidadDeBilletes;

	public Dispensador() {
		this.cantidadDeBilletes = new HashMap<Integer, Integer>();
		llenarDispensador();
	}

	public void llenarDispensador() {
		this.cantidadDeBilletes.put(100, 500); // 500 billetes de $100
		this.cantidadDeBilletes.put(500, 500); // 500 billetes de $500
		this.cantidadDeBilletes.put(1000, 500); // 500 billetes de $1000

	}

	public String retirarBillete(int dineroAretirar) { // Se podria hacer recursivo
		int billeteDe100 = 0;
		int billeteDe500 = 0;
		int billeteDe1000 = 0;
		boolean hayDinero = this.cantidadDeBilletes.get(100) * 100 + this.cantidadDeBilletes.get(500) * 500
				+ this.cantidadDeBilletes.get(1000) * 1000 >= dineroAretirar;
				
				
		if (hayDinero) {
			while (dineroAretirar >= 100) {
				while (dineroAretirar >= 1000) {
					if (this.cantidadDeBilletes.get(1000) > 0) {
						billeteDe1000++;
						dineroAretirar -= 1000;
					}
				}
				while (dineroAretirar >= 500) {
					if (this.cantidadDeBilletes.get(500) > 0) {
						billeteDe500++;
						dineroAretirar -= 500;
					}
				}
				while (dineroAretirar >= 100) {
					if (this.cantidadDeBilletes.get(1000) > 0) {
						billeteDe100++;
						dineroAretirar -= 100;
					}
				}
			}
		return "Retiraste " + billeteDe1000 + " billetes de 1000, " + billeteDe500 + " billetes de 500, " + billeteDe100
				+ " billetes de 100";
		}
		else {
			return "No hay dinero suficiente en el Dispensador";
		}
	}
}
