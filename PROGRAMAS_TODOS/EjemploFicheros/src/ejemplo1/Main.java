package ejemplo1;
/*
1. Programa que:
a) Pide ruta y nombre de un fichero y nos da información sobre él
b) Permite borrar un fichero
c) Lista los archivos contenidos en un directorio y muestra información sobre
ellos o lo crea si no existe
*/

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Introduce ruta:");
		String ruta=sc.nextLine();
		System.out.println("Introduce nombre del fichero:");
		String nombre=sc.nextLine();
		
		Path fichero=Paths.get(ruta+"/"+nombre);
		
		if (!Files.exists(fichero))
			System.out.println("No existe el fichero");
		else {
			if (Files.isDirectory(fichero))
				System.out.println("Es un directorio");
			else
				try {
					if (Files.isHidden(fichero))
						System.out.println("Es un fichero oculto");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error comprobando ficheros ocultos");;
				}
		}
		System.out.println("Quieres borrar el fichero?(S/N)");
		String respuesta=sc.nextLine();
		if (respuesta.equalsIgnoreCase("S")) {
			try {
				Files.delete(fichero);
			}catch (NoSuchFileException e) {
				System.out.println("Error, no está vacío el directorio a borrar");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error borrando archivo");
			}
		}
		else {
			if (!Files.isDirectory(fichero))
				System.out.println("No puedo listar los archivos porque no es un directorio");
			else {
				try {
					DirectoryStream<Path> stream = Files.newDirectoryStream(fichero);
					for (Path path : stream) {
							System.out.println(path.getFileName());
					}
				} catch (IOException e) {
					System.err.format("%s Error permisos\n", fichero);
				}
			}
		}

		
		

	}

}
