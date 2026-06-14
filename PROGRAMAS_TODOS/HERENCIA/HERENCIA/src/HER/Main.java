package HER;

import java.io.IOException;
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
				System.out.println("Tu contraseña es la: " + pass);
				Suscripcion encontrado = buscarMail(mail, suscripciones);
				if (encontrado != null) {
					System.out.println("¿Quieres cambiar de suscripción?(S/N)");
					String opcion = sc.nextLine();
					if (opcion.equalsIgnoreCase("S")) {
						if (encontrado instanceof Standard) {
							// suscripciones.remove(encontrado);
							borrarMail(mail, suscripciones);
							suscripciones.add(new Prime(mail, encontrado.getContraseña(), encontrado.getFechaFinal()));
							System.out.println("Cambiaste a Prime");
						} else {
							borrarMail(mail, suscripciones);
							suscripciones
									.add(new Standard(mail, encontrado.getContraseña(), encontrado.getFechaFinal()));
							System.out.println("Cambiaste a Standard");
						}
					} else {
						System.out.println("Perfecto, mantenemos tu suscripción como la tenías");
					}

				} else {
					System.out.println("¿Prime o Standard?");
					String tipo = sc.nextLine();
					if (tipo.equalsIgnoreCase("Prime")) {
						suscripciones.add(new Prime(mail, pass, LocalDate.now().plusMonths(1)));
						System.out.println("Alta en prime con éxtio");
					} else {
						suscripciones.add(new Standard(mail, pass, LocalDate.now().plusMonths(1)));
						System.out.println("Alta en standard con éxito");
					}
				}
				break;
			case 2:
				String email;
				try {
					System.out.println("Anota mail");
					email = sc.nextLine();
					Suscripcion encontradoSu = buscarMail(email, suscripciones);
					if (encontradoSu == null) {
						System.out.println("USUARIO NO ENCONTRADO");
					}
					if (encontradoSu.isConectado() == false) {
						int cont = 0;
						while (cont < 3) {
							System.out.println("Introduce contraseña");
							int passwd = sc.nextInt();
							if (!(passwd == encontradoSu.getContraseña())) {
								cont++;
							} else {
								break;
							}
						}
						if (cont == 3) {
							System.out.println("Intentos maximos superados");
						} else {
							sc.nextLine();
							System.out.println("Contraseña correcta");
							if (encontradoSu.getFechaFinal().isBefore(LocalDate.now())
									|| encontradoSu.limitePantallas() == true) {
								System.out.println("ERROR");
							} else {
								encontradoSu.conectar();
								System.out.println("Qué película vas a ver?");
								String titulo = sc.nextLine();
								encontradoSu.verPelicula(titulo);
							}
						}
					} else {
						if (encontradoSu.getFechaFinal().isBefore(LocalDate.now())
								|| encontradoSu.limitePantallas() == true) {
							System.out.println("ERROR");
						} else {
							encontradoSu.conectar();
							System.out.println("Qué película vas a ver?");
							String titulo = sc.nextLine();
							encontradoSu.verPelicula(titulo);
						}

					}
				} catch (Exception e) {
					e.getMessage();
				}

				break;
			case 3:
				System.out.println("Anota mail para salir");
				String mailSalir = sc.nextLine();
				Suscripcion desconectado = buscarMail(mailSalir, suscripciones);
				if (desconectado != null) {
					desconectado.desconectar();
					System.out.println("Te has desconectado");
				} else {
					System.out.println("Hubo un error al querer desconectarse");
				}
				break;
			case 4:
				for (Suscripcion s : suscripciones) {
					if (s.getFechaFinal().isAfter(LocalDate.now())) {
						s.cobrar();

						System.out.println("Quieres renovar tu suscripción?(S/N)");
						String renovar = sc.nextLine();
						if (renovar.equalsIgnoreCase("N")) {
							suscripciones.remove(s);
							System.out.println("Suscripción cancelada");
						} else {
							LocalDate nuevaCaducidad = s.getFechaFinal().plusMonths(1);
							s.setFechaFinal(nuevaCaducidad);
							System.out.println("Suscripción renovada!! :)");
						}
					}
				}
				break;
			case 5:
				System.out.println(suscripciones.toString());
				System.out.println("Nº total de peliculas con coste extra :" + Standard.getNumTotalPelisExtras()
						+ "\nNº total de peliculas sin coste extra "
						+ (Suscripcion.totalPelis - Standard.getNumTotalPelisExtras()) + "\nTotal recaudado :"
						+ Suscripcion.getTotalRecaudado());
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