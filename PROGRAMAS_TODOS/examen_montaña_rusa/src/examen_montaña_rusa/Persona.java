package examen_montaña_rusa;

public abstract class Persona {
	protected int edad;
	private int altura;
	protected static double totalRecaudado;
	public Persona(int edad, int altura) {
		super();
		this.edad = edad;
		this.altura = altura;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public abstract double pagar ();
	@Override
	public String toString() {
		return "Edad : " + edad + "\nAltura: " + altura;
	}

}
