package ejMontañaRusa;

public class Promocion extends Persona {

	private String codigo;

	public Promocion(int edad, int altura, String codigo) {
		super(edad, altura);
		this.codigo = codigo;
	}

	@Override
	public double pagar() {
		// últimos 2 dígitos
		return Integer.parseInt(codigo.substring(codigo.length() - 2));
	}
}