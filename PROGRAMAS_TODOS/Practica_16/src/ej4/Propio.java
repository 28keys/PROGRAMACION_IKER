package ej4;

public class Propio extends Empleado {
	private static int totalPropios = 0;
	private double beneficioTotal; 
	private double salarioBase;
	public Propio(String nombre, String dni, String direccion, String tfno, double salarioBase) {
		super(nombre, dni, direccion, tfno);
		this.salarioBase = salarioBase;
		totalPropios++;
		
	}

	public static int getTotalPropios() {
		return totalPropios;
	}

	public void dineroRepatido(double beneficio) {
		this.beneficioTotal += beneficio;

	}

	@Override
	public String toString() {
		return "\n" + "[[Empleado Propio]]" + "\n" + "--> " + "Beneficio : " + beneficioTotal + super.toString();
	}

	@Override
	public double pagar () {
		double pagoPorHora, totalGanancias;
		pagoPorHora = horas * 15;
		totalGanancias = salarioBase+pagoPorHora+beneficioTotal;
		horas = 0;
		beneficioTotal = 0;
		return totalGanancias;
	}


}