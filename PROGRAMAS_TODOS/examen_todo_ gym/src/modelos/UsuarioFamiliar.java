package modelos;

public class UsuarioFamiliar extends Usuario {

	public UsuarioFamiliar(String clave, int pin, String nombre, String apellidos, String tarifa, int numeromimebros,
			double descuento) {
		super(clave, pin, nombre, apellidos, tarifa, numeromimebros, descuento);
	}

	@Override
	public double pagar() {
		double precio = 0;

		if (numeromimebros == 3) {
			precio = 30;
		}
		if (numeromimebros == 4) {
			precio = 20;
		}
		if (numeromimebros > 4) {
			precio = 12;
		}
		return precio;
	}

}
