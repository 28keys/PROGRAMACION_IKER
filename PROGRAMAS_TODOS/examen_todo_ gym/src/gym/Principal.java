package gym;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.*;
import bbdd.*;
import modelos.Iker;
import modelos.Reserva;
import modelos.Usuario;

import static java.nio.file.StandardOpenOption.*;

public class Principal {
	/* Cuando te conectes a clase es el 3306, con contraseña root */
	/* Aqui en casa es el 3096 y sin contraseña */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		BD_Gym bd = new BD_Gym("mysql-properties.xml");
		Charset charset = Charset.forName("UTF-8");
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/LL/yy");
		HashSet<String> clases = new HashSet<String>();
		HashMap<String, Integer> codigoReservas = new HashMap<String, Integer>();
		LinkedList<Reserva> listaEspera = new LinkedList<Reserva>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			clases = bd.contarClases();
			codigoReservas = bd.contarReservas();
			listaEspera = bd.crearListaEspera(codigoReservas);
			for (Reserva r : listaEspera) {
				System.out.println(r);
			}
			usuarios = bd.crearUsuarios();
			for (Usuario u : usuarios) {
				u.pagar();
			}
		} catch (ErrorBaseDatos e) {
			e.printStackTrace();
		}
		try {

			BufferedReader reader = Files.newBufferedReader(Paths.get("reservas.txt"), charset);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] datos = line.split(",");
				Reserva rev = new Reserva(datos[1], datos[0]);
				rev.setFechaReserva(LocalDate.parse(datos[2], formato));
				boolean buscarUsuario = false;
				for (Usuario us : usuarios) {
					if (us.getClave().equals(rev.getUsuario())) {
						buscarUsuario = true;
						break;
					}
				}
				try {
					if (buscarUsuario == false || !clases.contains(datos[1])) {
						throw new Iker();
					} else {
						boolean plaza = false;
						try {
							plaza = bd.hayPlazas(rev.getCodigoclase());
							if (plaza) {
								int filas = bd.intertarReservas(rev);
								if (filas == 0) {
									System.out.println(filas + "actualizadas");
								} else {
									System.out.println(filas + " actualizadas");
									int filas2 = bd.actualizarPlazas(rev);
									if (filas2 == 0) {
										System.out.println(filas + " actualizadas");
									} else {
										System.out.println(filas + " actualizadas");
									}
								}
							} else {
								if (Integer.parseInt(datos[3]) == 1) {
									rev.setPreferencia(1);
								}

								listaEspera.add(rev);

							}
						} catch (ErrorBaseDatos e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (Iker e) {
					System.out.println("Error al leer la linea, no se encontró al usuario o clase");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.sort(listaEspera, new OrdenarReservas());
		String usuario;
		boolean encontrado = true;
		while (encontrado) {
			do {
				System.out.println("Anota usuario");
				usuario = sc.nextLine();
			} while (!usuario.matches("[A-Z]{3}[0-9]{2,3}"));
			ArrayList<Reserva> reservasUsuario = new ArrayList<Reserva>();
			try {
				reservasUsuario = bd.reservasUsuario(usuario);
				if (reservasUsuario == null) {
					encontrado = false;
				} else {

					for (int i = 0; i < reservasUsuario.size(); i++) {
						System.out.println(i + 1 + "." + reservasUsuario.get(i).getCodigoclase()
								+ reservasUsuario.get(i).getUsuario());
					}
					System.out.println("Anota reserva que quieres cancelar");
					int pos = sc.nextInt();
					int filas = bd.borrarReserva(pos - 1);
					if (filas == 0) {
						System.out.println(filas + " filas actualizadas");
					} else {
						System.out.println(filas + " filas actualizadas");
					}
				}
			} catch (ErrorBaseDatos e) {
				e.printStackTrace();
			}

		}
		
	}

}
