package ej2;

public class Main {

	public static void main(String[] args) {

		Alumno alumnos[] = new Alumno[3];
		alumnos[0] = new Alumno("Marcos", "Rodriguez", 3, 3800);
		alumnos[1] = new Extranjero("Lara", "Cuevas", 1, 2750, "España");
		alumnos[2] = new CursoPuente("Alicia", "Novikov", 4, 4000, "Derecho + ADE");
		int i;
		for (i = 0; i < 3; i++) {
			System.out.println(alumnos[i].toString());

		}

	}

}
