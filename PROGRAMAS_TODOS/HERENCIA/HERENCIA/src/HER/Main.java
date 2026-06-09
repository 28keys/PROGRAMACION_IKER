package HER;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opc;
		LinkedList<Suscripcion> suscripciones = new LinkedList<Suscripcion>();
		System.out.println("Escribe pelicula a fijar");
		String pelicula = sc.nextLine();
		do {
			System.err.println(
					"-------PLATAFORMAS--------\n1.Alta suscripción\n2.Ver pelicula\n3.Desconectar\n4.Cobrar usuario\n5.Mostrar peliculas");
			opc = sc.nextInt();
			sc.nextLine();
			switch (opc) {
			case 1:
				Random contraseña = new Random();
				//int pass = contraseña.nextInt(1000,10000);
				int pass = contraseña.nextInt(9000)+1000;
				System.out.println("Anota mail");
				String mail = sc.nextLine();
				do {
					System.out.println("Contraseña: " + pass);
				} while (!(pass > 999 || pass < 10000));
				System.out.println("Prime o Standard?");
				String tipo = sc.nextLine();
				if (tipo.equalsIgnoreCase("Prime")) {
					suscripciones.add(new Prime(mail, pass, LocalDate.now()));
				} else {
					suscripciones.add(new Standard(mail, pass, pelicula, LocalDate.now()));
				}

				boolean suscritoPrime = false;
				boolean suscritoStandard = false;
				for (int i = 0; i < suscripciones.size(); i++) {
					Suscripcion cambiar = suscripciones.get(i);
					if (mail.equals(suscripciones.get(i).getMail())) {
						if (cambiar instanceof Standard) {
							suscritoStandard = true;
							if (cambiar instanceof Prime) {
								suscritoPrime = true;
							}
						}
					}
					if (suscritoStandard) {
						System.out.println("Ya estas dado de alta quieres cambiar a Prime?(S/N)");
						String elige = sc.nextLine();
						if (elige.equalsIgnoreCase("S")) {
							for (int j = 0; j < suscripciones.size(); j++) {
								if (suscripciones.get(j) instanceof Prime) {
									suscripciones.set(j, cambiar);
									break;
								}
							}
						}

					}

					if (suscritoPrime) {
						System.out.println("Ya estas dado de alta quieres cambiar a Standard?");
						String elige2 = sc.nextLine();
						if (elige2.equalsIgnoreCase("S")) {
							for (int k = 0; k < suscripciones.size(); k++) {
								if (suscripciones.get(k) instanceof Standard) {
									suscripciones.set(k, cambiar);
									break;
								}
							}
						}
					}
					System.out.println("Suscripcion dada de alta");
				}
				break;
			case 2:
				System.out.println("Anota mail");
				String email = sc.nextLine();
				for (int i = 0; i < suscripciones.size(); i++) {
					Suscripcion m = suscripciones.get(i);

					if (m.getMail().equals(email)) {
						if (!(m.isConectado())) {
							int cont = 0;
							boolean caducado = false;
							while (cont < 3) {
								System.out.println("Intoduce tu contraseña");
								int passwd = sc.nextInt();
								sc.nextLine();
								if (!(passwd == m.getContraseña())) {
									cont++;
								} else if (m.getFechaSuscrito().isBefore(LocalDate.now())) {
									System.out.println("Tu suscripcion ha caducado");
									caducado = true;
								} else {
									m.conectar();
									System.out.println("Anota pelicula que quieres ver");
									String peli = sc.nextLine();
									m.verPelicula();
									break;
								}

							}
							if (cont == 3 || caducado == true) {
								System.out.println("ERROR al conectar con el usuario");
								System.exit(0);
							}

						} else {
							System.out.println("Anota pelicula");
							String peli = sc.nextLine();
							m.verPelicula();
							break;
						}
					} else {
						System.out.println("Mail incorrecto");
					}
				}
				break;
			case 3:
				System.out.println("Introduce correo");
				String correo = sc.nextLine();
				for (Suscripcion s : suscripciones) {
					if (s.getMail().equals(correo)) {
						s.desconectar();
						System.out.println("Te desconectaste");
					}
				}
				break;
			case 4:
				for (int i = 0; i < suscripciones.size(); i++) {
					if (suscripciones.get(i).getFechaSuscrito().isBefore(LocalDate.now().plusDays(1))) { //he puesto plus dayds para probarlo
						suscripciones.get(i).cobrar();
						System.out.println("Usuario cobrado");
						System.out.println("Deseas renovar tu suscripcion? (S/N)");
						String opcion = sc.nextLine();
						if (opcion.equalsIgnoreCase("N")) {
		
						}
					}

				}
				break;
			case 5:
				for (Suscripcion s : suscripciones) {
					System.out.println(s);
				}
				break;
			}

		} while (opc != 6);
	}

}