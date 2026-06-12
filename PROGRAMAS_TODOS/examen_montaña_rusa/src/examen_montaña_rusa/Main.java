package examen_montaña_rusa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashMap;
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
		HashMap<String, Integer> totalTipos = new HashMap<String, Integer>();

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
					Iterator<Persona> it = colaEspera.iterator();
					while (it.hasNext() && plazas < 4) {
						Persona añadirPersona = it.next();
						try {
							if (!(añadirPersona.getAltura() >= 100 && añadirPersona.getAltura() <= 190)) {
								throw new Iker();
							}
							añadirPersona.pagar();
							montañaRusa.añadiraAtraccion(añadirPersona);
							String tipoPersona = añadirPersona.getClass().getSimpleName();
							if (totalTipos.containsKey(tipoPersona)) {
								totalTipos.put(tipoPersona, totalTipos.get(tipoPersona) + 1);
							} else {
								totalTipos.put(tipoPersona, 1);
							}
							plazas++;

							it.remove();
						} catch (Iker ik) {
							System.out.println("No eres apto para subir , mides " + añadirPersona.getAltura() + "cm");
							it.remove();
						}

					}

				} else {
					System.out.println("Se necesita un minimo de 4 personas");
				}
				break;
			case 4:
				if (montañaRusa.estaLlena()) {
					montañaRusa.setEnMarcha(true);
					System.out.println("Atracción en marcha!!");
				} else {
					System.out.println("La atracción no está llena");
				}
				break;
			case 5:
				if (montañaRusa.isEnMarcha()) {
					montañaRusa.setEnMarcha(false);
					int borrarPlazas = 0;
					while (borrarPlazas < 4) {
						Persona borrada = montañaRusa.vaciarAtraccion();
						if (borrada != null && (borrada instanceof ConBono && ((ConBono) borrada).getViajes() > 0)) {
							colaEspera.addLast(borrada);
							System.out.println("Se te añadió a la cola de espera :\n" + borrada.getEdad() + " años\n"
									+ "Altura: " + borrada.getAltura() + "cm");
						}
						borrarPlazas++;
					}
				}
				break;
			case 6:
				System.out.println("-------------PERSONAS EN COLA DE ESPERA-----------");
				Iterator<Persona> ite = colaEspera.iterator();
				while (ite.hasNext()) {
					Persona personasEnCola = ite.next();
					System.out.println(personasEnCola);
				}
				System.out.println("--------------------------------------------------");
				System.out.println("------------PERSONAS QUE HAY EN LA ATRACCIÓN------");
				for (Persona p : montañaRusa.getPlazas()) {
					if (p != null) {
						System.out.println(p);
					}
				}
				System.out.println("--------------------------------------------------");
				System.out.println("TOTAL RECAUDADO : " + Persona.totalRecaudado + "€");
				System.out.println("--------------------------------------------------");
				if (montañaRusa.isEnMarcha()) {
					System.out.println("ATRACCIÓN EN MARCHA");
				} else {
					System.out.println("LA ATRACCIÓN NO ESTÁ EN MARCHA");
				}
				System.out.println("-----------TOTAL DE PERSONAS POR TIPO-------------");
				for (String clave : totalTipos.keySet()) {
					System.out.println(clave + " nº total de personas: " + totalTipos.get(clave));
				}
				System.out.println("--------------------------------------------------");
				break;
			}
		} while (opc != 7);

	}

}

/*
 * put(clave, valor) → guarda o sobreescribe. Si la clave no existe la crea, si
 * ya existe la machaca con el nuevo valor.
 * 
 */
