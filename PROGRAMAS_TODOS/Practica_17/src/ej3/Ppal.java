
package ej3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class Ppal {
	public static void main(String args[]) {
		// ArrayList<Student> al=new ArrayList<Student>();
		Vector<Student> al = new Vector();
		al.add(new Student(101, "Luis", 23, LocalDate.of(2025, 9, 15)));
		al.add(new Student(106, "Gemma", 27, LocalDate.of(2020, 2, 22)));
		al.add(new Student(105, "Pedro", 21, LocalDate.of(2016, 12, 4)));

		System.out.println("Alumnos ordenados por nombre");
		Collections.sort(al, new StudentPorNombre());
		for (Student st : al) {
			System.out.println(st);
		}

		System.out.println("Alumnos ordenados por edad");
		Collections.sort(al, new StudentPorEdad());
		for (Student st : al) {
			System.out.println(st);
		}
		
		System.out.println("Alumnos ordenados por fecha");
		Collections.sort(al, new StudentPorFecha());
		for (Student st : al) {
			System.out.println(st);
		}

	}
}
