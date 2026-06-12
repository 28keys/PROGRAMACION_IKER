package examenlibros;

import static java.nio.file.StandardOpenOption.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @descrition Clase que arbe un archivo en forma secuencial, con stream y
 *             buffer para leer las l�neas del archivo
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		double nota;
		LocalDate fecha;
		DateTimeFormatter d=DateTimeFormatter.ofPattern("dd/LL/yyyy");
		LinkedList<Auto> autos = new LinkedList<>();
		HashMap<String, Propietario> propietarios = new HashMap<String, Propietario>();
		Scanner sc = new Scanner(System.in);
		Path fpropietarios = Paths.get("propietario.info");
		Path flog = Paths.get("log.txt");
		Path fautos = Paths.get("auto.info");
		Charset charset = Charset.forName("UTF-8");
		BufferedReader readerP = null, readerA=null;
		BufferedWriter writer = null, baratos=null;

		if (!Files.exists(fpropietarios)) {
			System.out.println("No existe el fichero de propietarios");
			System.exit(0);
		}

		try {
			// Creamos un BuffereReader de java.io
			readerP = Files.newBufferedReader(fpropietarios, charset);
			writer = Files.newBufferedWriter(flog, charset, CREATE, TRUNCATE_EXISTING, WRITE);

			String line = null;
			while ((line = readerP.readLine()) != null) {
				String resultado[] = line.split(",");
				if (resultado.length != 3) {
					System.out.println("Número incorrecto de campos");
					writer.write("Fichero propietarios: Número incorrecto de campos\n");
				} else {
					propietarios.put(resultado[0],new Propietario(resultado[0],resultado[1],resultado[2]));
				}
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			writer.write("IOException en el fichero de propietarios");
		} finally {

			if (readerP != null) {
				readerP.close();
			}
		}

			
		try {
			// Creamos un BuffereReader de java.io
			readerA = Files.newBufferedReader(fautos, charset);

			String line = null;
			while ((line = readerA.readLine()) != null) {
				String resultado[] = line.split(",");
				if (resultado.length != 4) {
					System.out.println("Número incorrecto de campos");
					writer.write("Fichero autos: Número incorrecto de campos\n");
				} else {
					if (!propietarios.containsKey(resultado[3])) {
						System.out.println("No existe el propietario "+resultado[3]);
						writer.write("Fichero autos:No existe el propietario" + resultado[3]);
					}
					else {
						try {
						double precio=Double.parseDouble(resultado[2]);
						fecha=LocalDate.parse(resultado[1], d);
						Auto auto=new Auto(resultado[0],resultado[3],fecha,precio);
						int pos=buscar(autos,auto);
						autos.add(pos,auto);
						}catch(DateTimeParseException e) {
							System.out.println("Fecha incorrecta");
							writer.write("Fichero autos:Fecha incorrecta");
						}catch(NumberFormatException e) {
							System.out.println("Importe incorrecto");
							writer.write("Fichero autos:Importe incorrecto");
						}
					}
				}
			}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			writer.write("IOException en el fichero de propietarios");
		} finally {

			if (readerA != null) {
				readerA.close();
			}
			
			
		}
		
		// Volqueis en un archivo txt, baratos.txt. Todos los vehículos con un 
		// importe inferior a 2000 euros, añadiendo el nombre y apellido de su 
		// propietario
		try {
		Path fbaratos=Paths.get("baratos.txt");
		baratos=Files.newBufferedWriter(fbaratos,CREATE,WRITE,TRUNCATE_EXISTING);
		for(int i=0; i<autos.size(); i++) {
			Auto auto=autos.get(i);
			if (auto.getPrecio()<1000) {
				Propietario p=propietarios.get(auto.getPropietario());
				baratos.write(auto.toString()+","+p.getNomAp()+"\n");
			}
		}
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			writer.write("IOException en el fichero de bartos.txt");
		} finally {

			if (writer !=null)
				writer.close();
			
			if (baratos != null)
				baratos.close();
		}
		
		// Cerrar archivo  
		
	}
	
	public static int buscar(LinkedList<Auto> autos,Auto auto) {
		for(int i=0;i<autos.size();i++) {
			if (auto.getFechaCompra().isBefore(autos.get(i).getFechaCompra()))
				return i;
			else
				if (auto.getFechaCompra().equals(autos.get(i).getFechaCompra()) && 
						auto.getPrecio()<autos.get(i).getPrecio())
					return i;
		}
		return autos.size();
	}

}
