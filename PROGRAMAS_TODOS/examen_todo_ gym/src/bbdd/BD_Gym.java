package bbdd;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import gym.*;
import modelos.Reserva;
import modelos.Usuario;
import modelos.UsuarioFamiliar;
import modelos.UsuarioNormal;

import java.time.*;

public class BD_Gym extends BD_Conector {

	private static Statement s;
	private static ResultSet reg;
	private static ResultSet reg2;
	private static PreparedStatement p;
	private static PreparedStatement p2;

	public BD_Gym(String file) {
		super(file);
	}

	public HashSet<String> contarClases() throws ErrorBaseDatos {
		HashSet<String> clases = new HashSet<String>();
		try {
			this.abrir();
			p = c.prepareStatement("SELECT codigo FROM clases");
			reg = p.executeQuery();
			while (reg.next()) {
				clases.add(reg.getString("codigo"));
			}
			p.close();
			this.cerrar();
			return clases;
		} catch (SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public HashMap<String, Integer> contarReservas() throws ErrorBaseDatos {
		HashMap<String, Integer> claveValor = new HashMap<String, Integer>();
		try {
			this.abrir();
			p = c.prepareStatement("SELECT * FROM reservas");
			reg = p.executeQuery();
			while (reg.next()) {
				String codigo = reg.getString("codigoclase");
				if (!claveValor.containsKey(codigo)) {
					claveValor.put(codigo, 1);
				} else {
					claveValor.put(codigo, claveValor.get(codigo) + 1);
				}
			}
			p.close();
			this.cerrar();
			return claveValor;
		} catch (SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public LinkedList<Reserva> crearListaEspera(HashMap<String, Integer> codigoReserva) throws ErrorBaseDatos {
		LinkedList<Reserva> reservas = new LinkedList<Reserva>();

		try {
			this.abrir();
			p = c.prepareStatement("SELECT ocupadas, plazas FROM clases WHERE codigo = ?");
			p2 = c.prepareStatement("SELECT codigoclase, usuario FROM reservas WHERE codigoclase = ?");

			for (String clave : codigoReserva.keySet()) {
				String codigo = clave;
				int numeroReservas = codigoReserva.get(clave);
				p.setString(1, codigo);
				reg = p.executeQuery();
				if (reg.next()) {
					int ocupadas = reg.getInt("ocupadas");
					int plazas = reg.getInt("plazas");
					if (numeroReservas + ocupadas > plazas) {
						p2.setString(1, codigo);
						reg2 = p2.executeQuery();
						while (reg2.next()) {
							Reserva rev = new Reserva(reg2.getString("codigoclase"), reg2.getString("usuario"));
							rev.setFechaReserva(LocalDate.now());
							reservas.add(rev);

						}
					}
				}
			}
			p.close();
			p2.close();
			this.cerrar();
			return reservas;
		} catch (SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}

	}

	public ArrayList<Usuario> crearUsuarios() throws ErrorBaseDatos {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			this.abrir();
			p = c.prepareStatement("SELECT * FROM usuarios");
			reg = p.executeQuery();
			while (reg.next()) {
				String tarifa = reg.getString("tarifa");
				if (tarifa.equals("F")) {
					usuarios.add(new UsuarioFamiliar(reg.getString("clave"), reg.getInt("pin"), reg.getString("nombre"),
							reg.getString("apellidos"), tarifa, reg.getInt("numeromiembros"),
							reg.getDouble("descuento")));
				} else {
					usuarios.add(new UsuarioNormal(reg.getString("clave"), reg.getInt("pin"), reg.getString("nombre"),
							reg.getString("apellidos"), tarifa, reg.getInt("numeromiembros"),
							reg.getDouble("descuento")));
				}
			}
			p.close();
			this.cerrar();
			return usuarios;
		} catch (

		SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public boolean hayPlazas(String codigo) throws ErrorBaseDatos {
		boolean tienePlaza = false;
		try {
			this.abrir();
			p = c.prepareStatement("SELECT ? FROM clases WHERE ocupadas < plazas");
			p.setString(1, codigo);
			reg = p.executeQuery();
			if (reg.next()) {
				tienePlaza = true;
			}
			p.close();
			this.cerrar();
			return tienePlaza;
		} catch (

		SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public int intertarReservas(Reserva r) throws ErrorBaseDatos {
		int filas = 0;
		try {
			this.abrir();
			p = c.prepareStatement("INSERT INTO reservas (codigoclase,usuario) VALUES(?,?)");
			p.setString(1, r.getCodigoclase());
			p.setString(2, r.getUsuario());
			filas += p.executeUpdate();
			p.close();
			this.cerrar();
			return filas;
		} catch (

		SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public int actualizarPlazas(Reserva r) throws ErrorBaseDatos {
		int filas = 0;
		try {
			this.abrir();
			p = c.prepareStatement("UPDATE clases set ocupadas = ocupadas + 1 WHERE codigo = ?");
			p.setString(1, r.getCodigoclase());
			filas += p.executeUpdate();
			p.close();
			this.cerrar();
			return filas;
		} catch (

		SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public ArrayList<Reserva> reservasUsuario(String usuario) throws ErrorBaseDatos {
		ArrayList<Reserva> rev = new ArrayList<Reserva>();
		try {
			this.abrir();
			p = c.prepareStatement("SELECT * FROM reservas WHERE usuario = ?");
			p.setString(1, usuario);
			reg = p.executeQuery();
			while (reg.next()) {
				rev.add(new Reserva(reg.getString("codigoclase"), reg.getString("usuario")));
			}
			p.close();
			this.cerrar();
			return rev;
		} catch (

		SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public int borrarReserva(int posicion) throws ErrorBaseDatos {
		int filas = 0;
		try {
			this.abrir();
			p = c.prepareStatement("DELETE reservas WHERE numero = ?");
			p.setInt(1, posicion);
			filas += p.executeUpdate();
			p.close();
			this.cerrar();
			return filas;
		} catch (

		SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

}