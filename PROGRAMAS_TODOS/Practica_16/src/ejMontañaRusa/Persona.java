package ejMontañaRusa;

public abstract class Persona {
	protected int edad;
	protected int altura;

	public Persona(int edad, int altura) {
		this.edad = edad;
		this.altura = altura;
	}

	public int getEdad() {
		return edad;
	}

	public int getAltura() {
		return altura;
	}

	// Método abstracto (polimorfismo)
	public abstract double pagar();

	// Para ordenar
	public int compareTo(Persona p) {
		if (this.edad != p.edad) {
			return this.edad - p.edad;
		} else {
			return this.altura - p.altura;
		}
	}

	@Override
	public String toString() {
		return "Edad: " + edad + ", Altura: " + altura;
	}
}