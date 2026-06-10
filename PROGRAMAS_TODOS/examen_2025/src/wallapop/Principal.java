package wallapop;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;

import bbdd.*;


public class Principal {
	/* Cuando te conectes a clase es el 3306, con contraseña root */
	/* Aqui en casa es el 3096 y sin contraseña */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);
		int opc = 0;
		BD_Taller bd = new BD_Taller("mysql-properties.xml");


	}

}
