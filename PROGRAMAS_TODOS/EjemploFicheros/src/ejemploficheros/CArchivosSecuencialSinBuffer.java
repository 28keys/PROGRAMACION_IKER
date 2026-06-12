package ejemploficheros;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  @descrition Clase que nos permite leer un archivo byte a byte de forma secuencial sin buffer
 */
public class CArchivosSecuencialSinBuffer {
	
	public static void main(String[] args) {
		Path entrada = Paths.get("osos.jpg");
		Path salida = Paths.get("osos2.jpg");
		// Array de bytes para leer todos los bytes del archivo
		InputStream istream=null;
		OutputStream ostream=null;
		int c;
		try {
			//InputStream y OutputStream de java.io nos permiten trabajar byte a byte
			//Los generamos de forma eficiente utilizando java.nio
			istream = Files.newInputStream(entrada);
			ostream = Files.newOutputStream(salida);
			while ((c = istream.read()) != -1) {
				// Escribimos todos los bytes en el archivo salida
				// Después de ejecutar comprobar que es igual a entrada
				ostream.write(c);
			}
			istream.close();
			ostream.close();
		} catch (IOException io) {
			System.err.println(io);
		}
	}

}
