package ej3;

import java.util.HashSet;

public class Cuenta {
	protected double interes;
	private static int totalCuentas = 5000;
	private static final double interesCuentaN = 0.03;
	protected double saldo;
	private int nCuenta;
	HashSet<String> titulares = new HashSet<String>();

	public Cuenta(HashSet<String> titulares, double saldo) {
		interes = interesCuentaN; // este interes es de cuenta normal
		totalCuentas++;
		this.saldo = saldo;
		this.nCuenta = totalCuentas;
		this.titulares = titulares;
	}

	@Override
	public String toString() {
		return "Saldo = " + saldo + ", nCuenta = " + nCuenta + ", titulares = " + titulares + "]";
	}

	public boolean validarTitular(String titular) { // le paso por el main el titular y mira si lo tiene
		return titulares.contains(titular);
	}

	public double dineroProducido(int dias) { // le paso por el main los dias y devuelvo el dineroProducido
		double dineroProducido;
		dineroProducido = (saldo * interes * dias) / 365;
		return dineroProducido;
	}

}
