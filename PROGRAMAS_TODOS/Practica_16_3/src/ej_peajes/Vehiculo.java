package ej_peajes;

public class Vehiculo {
	private String matricula, tipo;

	public Vehiculo(String matricula, String tipo) {
		super();
		this.matricula = matricula;
		this.tipo = tipo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "\n-MATRICULA --> " + matricula + "\n-TIPO --> " + tipo;
	}

}
