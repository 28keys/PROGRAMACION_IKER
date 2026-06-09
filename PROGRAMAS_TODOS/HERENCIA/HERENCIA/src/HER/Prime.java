package HER;

import java.time.LocalDate;

public class Prime extends Suscripcion implements Iker {
	private int dispositivos = 3;

	public Prime(String mail, int contraseña, LocalDate fechaSuscrito) {
		super(mail, contraseña, fechaSuscrito);
	}

	@Override
	public void verPelicula() {
		numeroPeli++;
	}

	@Override
	public double cobrar() {
		double total;
		total = numeroPeli * 25;
		return total;
	}

}