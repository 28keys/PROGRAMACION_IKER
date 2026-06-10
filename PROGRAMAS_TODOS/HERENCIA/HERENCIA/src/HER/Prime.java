package HER;

import java.time.LocalDate;

public class Prime extends Suscripcion implements Iker {
	private static int dispositivos = 3;
	public Prime(String mail, int contraseña, LocalDate fechaSuscrito) {
		super(mail, contraseña, fechaSuscrito);
	}
	@Override
	public void verPelicula(String pelicula) {
		totalPelis+=1;
	}
	
	@Override
	public double cobrar() {
		double total = 25;
		totalRecaudado+=total;
		return total;
	}
	@Override
	public boolean limitePantallas() {
		if (numPantallas==dispositivos) {
			return true;
		}
		return false;
	}

}