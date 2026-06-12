package ej2;

public class CursoPuente extends Alumno {
	private String carreraProc;

	public CursoPuente(String nombre, String apellidos, int curso, double impMatricula, String carreraProc) {
		super(nombre, apellidos, curso, impMatricula);
		this.carreraProc = carreraProc;

	}

	@Override
	public String toString() {
		return super.toString() + " Carrera de Procedencia: [carreraProc=" + carreraProc + "]";
	}

}
