package comerciales;

public class Fijo extends Comercial {
	private static double fijo;
	private static double comision = 5;

	public Fijo(String nombre) {
		super(nombre);

	}

	public static void setFijo(double fijo) {
		Fijo.fijo = fijo;
	}

	@Override
	public String toString() {
		return "Fijo [" + super.toString() + "]";
	}

	@Override
	double pagar() {
		int totalM = nA + nB + nC;
		double total = 0;
		total = fijo + totalM * comision;
		ganado+=total;
		return total;
	}



}
/*Cuando recorres al añadir unidades  en el momento q estas get(i) estas posicionado en el indice por ej
 * Juan y ves que juan tiene su nombre, y sus unidades nA nB nC y ves que tien por ej 3 4 56 esas son las suyas
 * si fuera otro numero de empleado, Marisa, veras otras unidades. Porque cuando añaiste las unidades fuiste sumando en cada
 * empleado segun el get(i)*/


