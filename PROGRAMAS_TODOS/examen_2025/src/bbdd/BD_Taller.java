package bbdd;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import wallapop.*;

import java.time.*;

public class BD_Taller extends BD_Conector {

	private static Statement s;
	private static ResultSet reg;
	private static PreparedStatement p;

	public BD_Taller(String file) {
		super(file);
	}

	

}