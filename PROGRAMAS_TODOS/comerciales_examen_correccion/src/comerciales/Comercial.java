package comerciales;

public abstract class Comercial implements Comparable<Comercial> {
	private String nombre;
	private int numero;
	protected int nA, nB, nC;
	protected double ganado;
	private static int numeroTotal = 0;

	public Comercial(String nombre) {
		// inicialmente las unidades son 0 pq aún no ha vendido nada
		super();
		this.nombre = nombre;
		numeroTotal++; // entra aqui y le suma ,ahora vale 1 //entra aqui y le suma otro, ahora vale 2
		numero = numeroTotal; // numero = 1 // numero = 2 // y así... todo el rato
	}

	public int getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}

	public int añadirUnidades(String marca, int unidades) {

		if (marca.equalsIgnoreCase("A")) {
			nA += unidades;
		}

		if (marca.equalsIgnoreCase("B")) {
			nB += unidades;

		}

		if (marca.equalsIgnoreCase("C")) {
			nC += unidades;

		}
		return unidades;
	}

	public int getUnidadesMarca(String marca) {
		if (marca.equalsIgnoreCase("A")) {
			return nA;
		}

		if (marca.equalsIgnoreCase("B")) {
			return nB;
		}

		if (marca.equalsIgnoreCase("C")) {
			return nC;
		}
		return 0;

	}

	public int getnA() {
		return nA;
	}

	public int getnB() {
		return nB;
	}

	public int getnC() {
		return nC;
	}

	abstract double pagar();

	public double getGanado() {
		return ganado;
	}

	public int compareTo(Comercial c) {
		if (this.ganado > c.ganado) {
			return -1;
		}

		if (this.ganado < c.ganado) {
			return 1;
		}

		if (this instanceof Fijo && !(c instanceof Fijo))
			return -1;
		if (!(this instanceof Fijo) && c instanceof Fijo)
			return 1;
		return 0;

	}

	@Override
	public String toString() {
		return "Nombre =" + nombre + ", numero =" + numero + ", nA=" + nA + ", nB=" + nB + ", nC=" + nC + "\n"
				+ "Ganado " + ganado;
	}

}
