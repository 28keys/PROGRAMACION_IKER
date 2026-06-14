package examen_autos_por_iker_ej2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashSet<String> archivos = new HashSet<String>();
		System.out.println("Anota nombre de la primera carpeta");
		String directorio1 = sc.nextLine();
		System.out.println("Anota otro nombre de la segunda carpeta");
		String directorio2 = sc.nextLine();
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directorio1));
			DirectoryStream<Path> stream2 = Files.newDirectoryStream(Paths.get(directorio2));
			for (Path s : stream) {
				if (Files.isRegularFile(s)) {
					archivos.add(s.getFileName().toString().substring(0, s.getFileName().toString().length() - 4));
				}
			}
			stream.close();
			for (Path st : stream2) {
				if (Files.isRegularFile(st)) {
					archivos.add(st.getFileName().toString().substring(0, st.getFileName().toString().length() - 4));
				}
			}
			stream2.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<String> lista = new LinkedList<String>(archivos);
		Collections.sort(lista, new OrdenarArchivos());
		boolean primero = false;
		for (String li : lista) {
			if (primero == true) {
				System.out.print(",");
			}
			primero = true;
			System.out.print(li);
		}

	}

}
