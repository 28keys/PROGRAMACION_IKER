package HER;

import java.time.LocalDate;

public class Prime extends Suscripcion implements Iker {
	protected int dispositivos = 3;
	private static int numPelisPrime = 0;
	public Prime(String mail, int contraseña, LocalDate fechaSuscrito) {
		super(mail, contraseña, fechaSuscrito);
	}
	@Override
	public void verPelicula() {
		numPelisPrime+=1;
		totalPelis+=1;
	}

	@Override
	public double cobrar() {
		double total;
		total = numPelisPrime * 25;
		totalRecaudado+=total;
		return total;
	}

}