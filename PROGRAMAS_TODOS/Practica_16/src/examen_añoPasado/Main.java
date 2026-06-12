package examen_añoPasado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> colaEspera = new LinkedList<Integer>();

		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String entrada;
		System.out.println("Introduce tu fecha de nacimiento (dd/MM/yyyy)");
		entrada = sc.nextLine();
		LocalDate fechaNacimiento = LocalDate.parse(entrada, formatoFecha);

//		System.out.println(fechaNacimiento.format(formatoFecha));
	}

}
