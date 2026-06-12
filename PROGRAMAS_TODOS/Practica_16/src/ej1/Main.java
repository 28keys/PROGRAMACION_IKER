package ej1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<Libro> libreria = new ArrayList<>();
		libreria.add(new Libro("El hobbit", "J.R Tolkien", "Alfaguara", 30));
		libreria.add(new Texto("Programacion", "Felix Gómez", "Mac Grawn", 38, "DAW1", 10));
		Scanner sc = new Scanner(System.in);

		Iterator<Libro> it = libreria.iterator();

		while (it.hasNext()) {
			Libro l = it.next();
			System.out.println(l);
		}

		System.out.println("Anota precio");
		double precio = sc.nextDouble();
		for (Libro l : libreria) {
			if (l.getPrecio() < precio)
				System.out.println(l);
			
			Collections.sort(libreria);
			for (Libro l : libreria) {
				System.out.println(l.toString());
			}

		}
		System.out.println("A continuación muestro todos los cursos de los " + "libros de texto de mi libreria");

		for (Libro l : libreria) {
			if (l instanceof Texto)
				// if(l.getClass().getSimpleName().equals("Texto")) //dame el nombre de la clase
				// al q pertenece este objeto
				System.out.println(((Texto) l).getCurso());
		}
	}

}
