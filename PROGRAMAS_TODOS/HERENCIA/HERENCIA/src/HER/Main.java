package HER;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opc;
		LinkedList<Suscripcion> suscripciones = new LinkedList<Suscripcion>();
		HashSet<String> peliculas = new HashSet<String>();
		String pelicula;
		do {
			System.out.println("Escribe peliculas a fijar (o salir)");
			pelicula = sc.nextLine();
			if (!pelicula.equalsIgnoreCase("salir"))
				peliculas.add(pelicula);
		} while (!pelicula.equalsIgnoreCase("salir"));

		Standard.añadirPeliculas(peliculas); // Lo llamo porque es Static y me deja
		do {
			System.err.println(
					"-------PLATAFORMAS--------\n1.Alta suscripción\n2.Ver pelicula\n3.Desconectar\n4.Cobrar usuario\n5.Mostrar peliculas");
			opc = sc.nextInt();
			sc.nextLine();
			switch (opc) {
			case 1:
				Random contraseña = new Random();
				// int pass = contraseña.nextInt(1000,10000);
				int pass = contraseña.nextInt(9000) + 1000;
				System.out.println("Anota mail");
				String mail = sc.nextLine();
				Suscripcion encontrado = buscarMail(mail, suscripciones);
				if (encontrado != null) {
					System.out.println("¿Quieres cambiar de suscripción?(S/N)");
					String opcion = sc.nextLine();
					if (opcion.equalsIgnoreCase("S")) {
						if (encontrado instanceof Standard) {
							// suscripciones.remove(encontrado);
							borrarMail(mail, suscripciones);
							suscripciones.add(new Prime(mail, encontrado.getContraseña(), encontrado.getFechaFinal()));
						} else {
							borrarMail(mail, suscripciones);
							suscripciones
									.add(new Standard(mail, encontrado.getContraseña(), encontrado.getFechaFinal()));
						}
					}
					else
						System.out.println("Perfecto, mantenemos tu suscripción como la tenías");
						
				} else {
					System.out.println("¿Prime o Standard?");
					String tipo = sc.nextLine();
					if (tipo.equalsIgnoreCase("Prime")) {
						suscripciones.add(new Prime(mail, pass, LocalDate.now().plusMonths(1)));
					} else {
						suscripciones.add(new Standard(mail, pass, LocalDate.now().plusMonths(1)));
					}
				}
				break;
			case 2:
				String email;
				System.out.println("Anota mail");
				email = sc.nextLine();
				Suscripcion mailEncontrado = buscarMail(email, suscripciones);
				if(mailEncontrado.isConectado()==false) {
					//System.out.println("Hola "+correo.substring(1,correo.length()-4));
				}

				break;
			case 3:
				break;
			case 4:

				break;
			case 5:
				break;
			}

		} while (opc != 6);
	}

	public static Suscripcion buscarMail(String mail, LinkedList<Suscripcion> suscripciones) {
		for (int i = 0; i < suscripciones.size(); i++) {
			if (suscripciones.get(i).getMail().equals(mail)) {
				return suscripciones.get(i);
			}
		}
		return null;
	}

	public static Suscripcion borrarMail(String mail, LinkedList<Suscripcion> suscripciones) {
		Suscripcion s = null;
		for (int i = 0; i < suscripciones.size(); i++) {
			if (suscripciones.get(i).getMail().equals(mail)) {

				s = suscripciones.remove(i);
				break;
			}
		}
		return s;
	}

}