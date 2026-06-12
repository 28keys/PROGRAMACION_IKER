package bbdd;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import modelos.Arreglo;
import modelos.Cliente;
import modelos.ClienteConCarnet;
import modelos.ClienteSinCarnet;
import taller.*;

import java.time.*;

public class BD_Taller extends BD_Conector {

	private static Statement s;
	private static ResultSet reg;
	private static PreparedStatement p;

	public BD_Taller(String file) {
		super(file);
	}

	public LinkedList<Arreglo> buscarArreglosPendientes() throws ErrorBaseDatos {
		LinkedList<Arreglo> arreglos = new LinkedList<Arreglo>();
		try {
			this.abrir();
			p = c.prepareStatement("SELECT * FROM arreglos WHERE fechaSalida IS NULL");
			reg = p.executeQuery();
			while (reg.next()) {
				Date fechaSalida = reg.getDate("fechaSalida");

				LocalDate fechaSalidaCorrecta = null;

				if (fechaSalida != null) {
					fechaSalidaCorrecta = fechaSalida.toLocalDate();
				}

				arreglos.add(new Arreglo(reg.getInt("idFactura"), reg.getInt("idMecanico"), reg.getString("matricula"),
						reg.getDate("fechaEntrada").toLocalDate(), fechaSalidaCorrecta, reg.getDouble("importe")));
			}
			p.close();
			this.cerrar();
			return arreglos;
		} catch (SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public Cliente buscarCliente(Arreglo arregloEncontrado) throws ErrorBaseDatos {
		Cliente clienteArreglo = null;
		// no se puede crear objetos con clase Cliente porque es abstract tiene que ser
		// la de sus hijas
		// como ClienteSinCarnet o ConCarnet
		try {
			this.abrir();
			p = c.prepareStatement(
					"SELECT * FROM clientes WHERE dni IN (SELECT idCliente FROM vehiculos WHERE matricula IN (SELECT matricula FROM arreglos WHERE matricula = ?))");
			p.setString(1, arregloEncontrado.getMatricula());
			reg = p.executeQuery();
			if (reg.next()) {
				if (reg.getInt("carnet") == 0) {
					clienteArreglo = new ClienteSinCarnet(reg.getString("dni"), reg.getString("nombre"),
							reg.getString("telefono"), reg.getString("direccion"), reg.getInt("carnet"),
							reg.getInt("visitas"));
				} else {
					clienteArreglo = new ClienteConCarnet(reg.getString("dni"), reg.getString("nombre"),
							reg.getString("telefono"), reg.getString("direccion"), reg.getInt("carnet"),
							reg.getInt("visitas"));
				}

			}
			p.close();
			this.cerrar();
			return clienteArreglo;
		} catch (SQLException e) {
			throw new ErrorBaseDatos("No se pudo encontrar los arreglos");
		}
	}

	public int actualizarArreglos(Arreglo encontrado) throws ErrorBaseDatos {
		int filas = 0;
		try {
			this.abrir();
			p = c.prepareStatement("UPDATE arreglos SET fechaSalida = ? , importe = ? WHERE matricula = ?");
			p.setDate(1, Date.valueOf(encontrado.getFechaSalida()));
			p.setDouble(2, encontrado.getImporte());
			p.setString(3, encontrado.getMatricula());
			filas = p.executeUpdate();
			p.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			throw new ErrorBaseDatos("No se pudo modificar los arreglos");
		}
	}
	
	public int actualizarVisitas(Cliente clienteArreglo) throws ErrorBaseDatos {
		int filas2 = 0;
		try {
			this.abrir();
			p = c.prepareStatement("UPDATE clientes SET visitas = ?  WHERE dni = ?");
			p.setInt(1, clienteArreglo.getVisitas());
			p.setString(2,clienteArreglo.getDni());
			filas2 = p.executeUpdate();
			p.close();
			this.cerrar();
			return filas2;
		} catch (SQLException e) {
			throw new ErrorBaseDatos("No se pudo modificar las visitas de los Clientes");
		}
	}

}