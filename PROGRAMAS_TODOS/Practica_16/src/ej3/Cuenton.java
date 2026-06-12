package ej3;

import java.util.ArrayList;
import java.util.HashSet;

public class Cuenton extends Cuenta {
	private static final double minimo = 600;
	private static final double interesCuentonC = 0.04;

	public Cuenton(HashSet<String> titulares, double saldo) {

		super(titulares, saldo);
		interes = interesCuentonC; // este interes es de cuenta de tipo CUENTON
		this.titulares = titulares;
		this.saldo = saldo;

	}

	public static double getMinimo() {
		return minimo;
	}

	@Override
	public String toString() {
		return "Cuenton :" + super.toString();
	}

}
