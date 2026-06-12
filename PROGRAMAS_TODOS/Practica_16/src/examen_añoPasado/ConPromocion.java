package examen_añoPasado;

public class ConPromocion extends Persona {
	private String codigo = "^[a-zA-Z]{3} [0-9] {2}$";

	public ConPromocion(int edad, double altura, String codigo) {
		super(edad, altura);
		this.codigo = codigo;
	}

}
