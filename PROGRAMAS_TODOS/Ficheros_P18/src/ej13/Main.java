package ej13;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce nombre del fichero");
		String nombre = sc.nextLine();
		Path fichero = Paths.get(nombre);
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		Charset charset = Charset.forName("UTF-8");
		try {
			BufferedReader reader = Files.newBufferedReader(fichero);
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] datos = line.split(",");
				Alumno nuevo = new Alumno(datos[0], datos[1], Double.parseDouble(datos[2]), LocalDate.parse(datos[3]));
				alumnos.add(nuevo);
			}
			for (int i = 0; i < alumnos.size(); i++) {
				for (int j = i + 1; j < alumnos.size(); j++) {
					if (alumnos.get(j).getNota() > alumnos.get(i).getNota()) {
						Alumno al = alumnos.get(i);
						alumnos.set(i, alumnos.get(j)); /*Si resulta que j es mayor que i j va primero por lo que
						la pos i pasa a ser la de j*/
						alumnos.set(j, al); /*y la que estaba en i se mueeve a j*/
						
						/*Es decir, si pepe tiene mas notas que laura, pepe se mueve a la pos
						 * de laura y laura a la de pepe*/
					}
				}
			}
			System.out.println("Alumnos ordenados por nota:");
			for (Alumno a : alumnos) {
				System.out.println(a);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
