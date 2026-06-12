package ej_peajes;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

public abstract  class Cabina {
	private int numeroCabina;
	private LocalTime tiempoAbierto;
	private int minutosAbierto;
	private boolean estado;
	protected LinkedList<Vehiculo> cola; // puede entrar aquí porque estan en el mismo package
	private int vehiculosAtendidos;
	private static int vehiculosAtendidosTotal;
	
//	protected double ganancias;
//	protected static double gananciasTot;
	
	public Cabina(boolean estado, int numero) {
		super();
		this.estado = false;
		this.numeroCabina=numero;
		this.cola = new LinkedList<Vehiculo>();
	}

	public int getNumeroCabina() {
		return numeroCabina;
	}

	public int calcularTiempoAbierto() {
		long minutos;
		minutos = ChronoUnit.MINUTES.between(tiempoAbierto, LocalTime.now());
		return (int) minutos;
	}
	public boolean abrir() {
		return this.estado = true;

	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstadoFalse() {
		this.estado = false;
	}

	public LinkedList<Vehiculo> getCola() {
		return cola;
	}

	public void setCola(LinkedList<Vehiculo> cola) {
		this.cola = cola;
	}

	public void addVehiculo(Vehiculo v) {
		this.cola.add(v);
		/* aqui añado en la cola de la cabina el vehiculo */
	}
	
	public abstract double pagar (double km);
	
	public void atender() {
	  this.vehiculosAtendidos++;
	  this.vehiculosAtendidosTotal++;
	}
	
	

	public static int getVehiculosAtendidosTotal() {
		return vehiculosAtendidosTotal;
	}

	public int getVehiculosAtendidos() {
		return vehiculosAtendidos;
	}

	public LocalTime getTiempoAbierto() {
		return tiempoAbierto;
	}

	public void setTiempoAbierto(LocalTime tiempoAbierto) {
		this.tiempoAbierto = tiempoAbierto;
	}
	@Override
	public String toString() {
		String cadena = "";
		if (estado == true) {
			cadena = "-CABINA: ABIERTA\n";
			cadena = cadena + "Vehículos esperando pagar....\n";
			for (Vehiculo v : cola) {
				cadena = cadena + v.toString(); // en v.tostring me saldría la matricula y el el tipo de vehiculo
			}
			cadena = cadena + "\n--------------------------------------";
		} else {
			cadena = "-CABINA : CERRADA\n" + "Con los vehiculos :";
			for (Vehiculo v : cola) {
				cadena = cadena + v.toString();

			}
			cadena = cadena + "\n-----------------------------------------";
		}
		return cadena;
	}

}
/*El toString tiene logica, el sentido es
 *cadena = "-CABINA : CERRADA\n" + "Con los vehiculos :"; 
 *cadena = cadena + v.toString(); 
 *cadena = cadena + "\n-----------------------------------------";
*
*Si hubiera algo en el for:
*-CABINA: CERRADA
*Con los vehiculos
*MATRICULA -->2713JSS
*TIPO--> turismo
*-------------------
*Si no:
*-CABINA: CERRADA
 * Con los vehiculos:
 * 
 * -------------------
 * 
 * 
 * 
 * 
 * */
