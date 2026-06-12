package examen_montaña_rusa;

public class Codigo extends Persona {
	private String codigo;

	public Codigo(int edad, int altura, String codigo) {
		super(edad, altura);
		this.codigo = codigo;
	}

	@Override
	public double pagar() {
		double total = Double.parseDouble((codigo.substring(codigo.length() - 2)));
		totalRecaudado += total;
		return total;
	}

	@Override
	public String toString() {
		return "CodigoPromocional: Tu codigo es el :" + codigo + "\n" + super.toString();
	}

}
