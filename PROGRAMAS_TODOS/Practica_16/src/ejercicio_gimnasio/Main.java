package ejercicio_gimnasio;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import ej5.SociosPorAlfabeto;
import ej5.SociosPorEdad;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opc, tipoSocio;
		String nombre, apellido, telefono, codigoSocio;
		String fechaNacimiento;
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LinkedList<Socio> socios = new LinkedList<Socio>();
		do {
			System.out.println("--------------");
			System.out.println("1. Dar de alta");
			System.out.println("2. Entrar al gym");
			System.out.println("3. Salir del gym");
			System.out.println("4. Informacion visitas y horas al gym");
			System.out.println("5. Cobrar mensualidad");
			System.out.println("6. Mostrar socios");
			System.out.println("7. Salir");
			System.out.println("--------------");
			opc = sc.nextInt();
			switch (opc) {
			case 1:
				System.out.println("1 = Socio preferente 2 = Socio normal");
				tipoSocio = sc.nextInt();
				sc.nextLine();
				System.out.println("Introduce tu nombre");
				nombre = sc.nextLine();
				System.out.println("Introduce tu apellido");
				apellido = sc.nextLine();
				try {
					buscarSocio(socios, nombre, apellido);

					System.out.println("Introduce tu telefono");
					telefono = sc.nextLine();

					System.out.println("Introduce tu fecha de nacimiento(dd/MM/yyyy)");
					try {
						fechaNacimiento = sc.nextLine();
						LocalDate fecha = LocalDate.parse(fechaNacimiento, formatoFecha);
						if (tipoSocio == 1)
							socios.add(new Preferente(nombre, apellido, telefono, fecha));
						else
							socios.add(new Resto(nombre, apellido, telefono, fecha));
					} catch (DateTimeParseException e) {
						System.out.println(e);
					}
				} catch (ExcepcionSocioRepetido e) {
					System.out.println("Este socio ya existe");
				}
				break;
			case 2:
				sc.nextLine();
				System.out.println("Anota codigo:");
				codigoSocio = sc.nextLine();
				boolean encontrado = false;
				for (Socio s : socios) {
					// for(int i=0;i<socios.size();i++){
					// socios.get(i).getCodigo().equals(codigo)
					if (s.getCodigoSocio().equalsIgnoreCase(codigoSocio)) {
						if (s.entradaGimnasio())
							System.out.println("Ha entrado con éxito ");
						else
							System.out.println("Ya estaba dentro ");
						encontrado = true;
						break;
					}
				}
				if (!encontrado)
					System.out.println("No existe el socio");
				break;
			case 3:
				sc.nextLine();
				System.out.println("Introduce tu codigo de socio");
				codigoSocio = sc.nextLine();
				Socio s = buscarCodigo(socios, codigoSocio);
				if (s == null) {
					System.out.println("No existe ese socio");
				} else {
					long resultado = s.salirGimnasio();
					if (resultado == 0) {
						System.out.println("El socio no estaba dentro");
					} else {
						System.out.println("Salida registrada a los " + resultado + " segundos");
					}
				}
				break;
			case 4:
				System.out.println(socios.toString());
				break;
			case 5:
				for (Socio so : socios) {
					if (so instanceof Preferente)
						System.out.println(
								so + "\n" + "Tiene que pagar como socio preferente " + so.cobrarMensualidad() + " €");
					if (so instanceof Resto)
						System.out.println(
								so + "\n" + "Tiene que pagar como socio normal " + so.cobrarMensualidad() + " €");
				}
				break;

			case 6:
				int elige;
				System.out.println("1 = ¿Alfabeticamente o de  2 = menor a mayor edad ?");
				elige = sc.nextInt();
				if (elige == 1) {
					Collections.sort(socios, new SociosPorAlfabeto());
					for (Socio soc : socios) {
						System.out.println(soc);
					}
				} else {
					Collections.sort(socios, new SociosPorEdad());
					for (Socio soc : socios) {
						System.out.println(soc);
					}
				}
				break;
			}

		} while (opc != 7);

	}

	public static void buscarSocio(LinkedList<Socio> socios, String nombre, String apellido)
			throws ExcepcionSocioRepetido {
		for (Socio s : socios) {
			if (s.getNombre().equalsIgnoreCase(nombre) && s.getApellido().equalsIgnoreCase(apellido))
				throw new ExcepcionSocioRepetido("Este socio ya existe");
		}
	}

	public static Socio buscarCodigo(LinkedList<Socio> socios, String codigoSocio) {
		for (Socio s : socios) {
			if (s.getCodigoSocio().equalsIgnoreCase(codigoSocio))
				return s;

		}
		return null;

	}
}