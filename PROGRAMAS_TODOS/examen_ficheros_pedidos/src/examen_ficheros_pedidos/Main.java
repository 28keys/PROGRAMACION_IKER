package examen_ficheros_pedidos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import static java.nio.file.StandardOpenOption.*;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		LinkedList<Pedido> pedidos = new LinkedList<Pedido>();
		HashSet<String> destinos = new HashSet<String>();
		try {
			DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("iker"));
			for (Path s : stream) {

				if (!s.getFileName().toString().startsWith("pedidos")) {
					Files.deleteIfExists(s);
				}

				BufferedReader reader = Files.newBufferedReader(s, charset);
				BufferedWriter writerLog = Files.newBufferedWriter(Paths.get("log.txt"), charset, CREATE, APPEND);
				String line = null;
				while ((line = reader.readLine()) != null) {
					String[] datos = line.split(",");
					if (!(datos[2].matches("[a-z]{5,6}[0-9]{1,2}"))) {
						System.out.println("HUBO ALGUN ERROR EN ALGUNA LINEA");
						writerLog.write("Error en la linea : " + line);
						writerLog.newLine();
					} else {
						pedidos.add(new Pedido(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]), datos[2],
								datos[3]));
					}
				}
				reader.close();
				writerLog.close();
			}
			stream.close();
			Collections.sort(pedidos, new OrdenarPedidos());
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Pedido p : pedidos) {
			destinos.add(p.getDestino());
		}
		System.out.print("Destinos:" + destinos); // no recorrar nada ya lo imprime solo las , y los []

		HashMap<String, Integer> pedidosImporte = new HashMap<String, Integer>();
		for (int i = 0; i < pedidos.size(); i++) {
			String codigoUsuario = pedidos.get(i).getCodigoUsuario();
			int numeroPedidos = pedidos.get(i).getNumeroPedio();

			if (pedidosImporte.containsKey(codigoUsuario)) {
				pedidosImporte.put(codigoUsuario, pedidosImporte.get(codigoUsuario) + numeroPedidos);
			} else {
				pedidosImporte.put(codigoUsuario, numeroPedidos);
			}
		}
		System.out.println();
		System.out.println("Totales: " + pedidosImporte);

		Iterator<Pedido> ite = pedidos.iterator();
		while (ite.hasNext()) {
			Pedido p = ite.next();
			String nombreArchivo = p.getCodigoUsuario();
			try {
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(nombreArchivo + ".txt"), charset, CREATE, APPEND);
				String lineaNueva = "Número:" + p.getNumeroPedio() + "," + "importe:" + p.getPrecio();
				writer.write(lineaNueva);
				writer.newLine();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	
	}

}
