package examen_ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Charset charset = Charset.forName("UTF-8");
		Path carpeta = Paths.get("Iker");
		LinkedList<Pedido> pedidos = new LinkedList<Pedido>();
		if (!Files.isDirectory(carpeta)) {
			System.out.println("No se encuentra la carpeta");
		} else {

			try {
				DirectoryStream<Path> stream = Files.newDirectoryStream(carpeta);
				for (Path s : stream) {
					if (!s.getFileName().startsWith("pedidos.txt")) {
						Files.deleteIfExists(s);
					}
				}
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String Iker = "Iker";
		String pedido = "pedidos.txt";

		Path pPedido = Paths.get(Iker, pedido); // con esto va a la ruta donde esta pedido.txt
		if (!Files.isRegularFile(pPedido)) {
			System.out.println("No se encontró el fichero");
		} else {

			try {
				BufferedReader reader = Files.newBufferedReader(pPedido, charset);
				String line = null;
				while ((line = reader.readLine()) != null) {
					String[] datosPedido = line.split(",");

					int numero = Integer.parseInt(datosPedido[0]);
					double precio = Double.parseDouble(datosPedido[1]);
					String codigo = datosPedido[2];
					String destino = datosPedido[3];
					if (!codigo.matches("[a-zA-Z]{5,6}[0-9]{1,2}")) {
						System.out.println("Error al leer codigo de la linea " + line);
					} else {
						Pedido nuevoPedido = new Pedido(numero, precio, codigo, destino);
						boolean insertado = false;
						for (int i = 0; i < pedidos.size(); i++) {
							if (nuevoPedido.compareTo(pedidos.get(i)) < 0) {
								pedidos.add(i, nuevoPedido);
								insertado = true;
								break; // importante meter el break pq sino se suma y sigue
								/* porque va creciendo el linkedlist y nunca termina */
							}
						}

						if (insertado == false) { // no ha habido ninguno que sea menor asiq lo pone al final de la cola
							pedidos.add(nuevoPedido);
						}
						System.out.println("Pedidos extraidos");
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			HashSet<String> destinos = new HashSet<String>(); // el hashset no admite duplicados
			for (int i = 0; i < pedidos.size(); i++) {
				destinos.add(pedidos.get(i).getDestino());
			}
			System.out.println("Desintos :" + destinos);

			HashMap<String, Double> totales = new HashMap<String, Double>();
			for (Pedido p : pedidos) {
				String codigoUsuario = p.getCodigoUsuario();
				double precio = p.getPrecio();

				if (totales.containsKey(codigoUsuario)) {
					double precioAntiguo = totales.get(codigoUsuario);
					totales.put(codigoUsuario, precioAntiguo + precio);
				} else {
					totales.put(codigoUsuario, precio);
				}
			}
			System.out.print("Totales :{");
			int cont = 0;
			for (String codigo : totales.keySet()) {
				double precioTotal = totales.get(codigo);
				if (cont > 0) {
					System.out.print(", ");
				}
				System.out.print(codigo + "=" + precioTotal);
				cont++;

			}
			System.out.print("}");

			// 1. Enganchas la aguja (Iterator) al conjunto de claves (keySet)
			Iterator<String> it = totales.keySet().iterator();
			// totales.keySet() me devuelve un conjunto con todas las claves, en este caso
			// los codigos
			while (it.hasNext()) {
				String usuario = it.next();
				Path pUsuario = Paths.get(Iker, usuario + ".txt"); // Acuérdate de guardarlo dentro de tu carpeta "Iker"
				try {
					BufferedWriter writer = Files.newBufferedWriter(pUsuario, charset);
					Iterator<Pedido> ite = pedidos.iterator();
					while (ite.hasNext()) {
						Pedido pedidoActual = ite.next();
						if (usuario.equals(pedidoActual.getCodigoUsuario())) {
							String line = "Número:" + pedidoActual.getNumeroPedido() + ",importe:"
									+ pedidoActual.getPrecio();
							writer.write(line);
							writer.newLine();
						}
					}
					writer.close();
					System.out.println("Pedidos creados en sus ficheros!");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
