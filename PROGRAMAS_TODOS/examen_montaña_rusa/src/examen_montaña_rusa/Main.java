package examen_montaña_rusa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Atraccion montañaRusa = new Atraccion();
		LinkedList<Persona> colaEspera = new LinkedList<Persona>();
		DateTimeFormatter patron = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String[] tipoEntrada = new String[3];
		tipoEntrada[0] = "Normal";
		tipoEntrada[1] = "ConBono";
		tipoEntrada[2] = "Codigo";
		int opc;
		do {
			System.out.println(
					"-----------LA MONTAÑA RUSA----------\n1.LLegar a la atracción\n2.Ordenar cola\n3.Montar en la atracción\n4.Poner en marcha\n5.Vaciar atracción\n6.Información");
			opc = sc.nextInt();
			sc.nextLine();
			switch (opc) {
			case 1:
				String fecha;
				LocalDate fechaNac = null;
				do {
					System.out.println("Anota fecha de nacimiento (dd/MM/yyyy)");
					fecha = sc.nextLine();
					try {
						fechaNac = LocalDate.parse(fecha, patron);
					} catch (Exception e) {
						System.out.println("Fecha en formato incorrecto");
					}

				} while (fechaNac == null);

				int edad = (int) ChronoUnit.YEARS.between(fechaNac, LocalDate.now());
				Random aleatorio = new Random();
				int altura = aleatorio.nextInt(70) + 140;
				System.out.println("Mides " + altura + "cm");
				Random aleatorioCliente = new Random();
				int tipo = aleatorioCliente.nextInt(tipoEntrada.length) + 1;

				if (tipo == 1) {
					colaEspera.addLast(new Normal(edad, altura));
				}

				if (tipo == 2) {
					colaEspera.addLast(new ConBono(edad, altura));
				}

				if (tipo == 3) {
					String codigo;
					do {
						System.out.println("Anota tu codigo promocional");
						codigo = sc.nextLine();
					} while (!codigo.matches("[A-Z]{1,3}[0-9]{1,2}"));

					colaEspera.addLast(new Codigo(edad, altura, codigo));
				}
				break;
			case 2:
				Collections.sort(colaEspera, new OrdenarCola());
				for (Persona cola : colaEspera) {
					System.out.println(cola);
				}
				break;
			case 3:
				if (colaEspera.size() >= 4) {
					int plazas = 0;
					try {
						Iterator<Persona> it = colaEspera.iterator();
						while (it.hasNext() && plazas < 4) {
							Persona añadirPersona = it.next(); // solo 1 vez si lo llamo de nuevo avanza otra posa
							if (!(añadirPersona.getAltura() >= 100 && añadirPersona.getAltura() <= 190)) {
								throw new Iker();
							} else {
								añadirPersona.pagar();
								montañaRusa.añadiraAtraccion(añadirPersona);
								plazas++;
								it.remove();
							}
						}
					} catch (Iker e) {
						System.out.println("No eres apto para subir a la atracción");
					}

				}
				System.out.println("Se necesita un minimo de 4 personas");

				break;
			case 4:
				if (montañaRusa.getPlazas().length == 4) {
					montañaRusa.setEnMarcha(true);
				} else {
					System.out.println("La atraccion no está llena");
				}
				break;
			case 5:
				// si tiene bono primero necesito la posicion del objeto q tengo en montañarusa?
				// en el array?, 
				//hacer protected el numero de viajes?
				break;
			}
		} while (opc != 7);

	}

}
