package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	// Parámetros de la conexión
	private static String database = "tienda";
	private static String user = "user";
	private static String password = "123456";
	private static String url="jdbc:mysql://localhost:3306/"+database;
	
	private Connection conexion=null;
	
	public Connection getConexion() {
		
		if (conexion!=null) {
			System.out.println(
					"DEvolviendo conexión creada anteriormente");
			return this.conexion;
		}
		// No hay conexión
		
		try {
			// Resgistramos el driver mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Obtener una conexión desde el driver
			this.conexion = DriverManager.getConnection(
					url, user, password);
			this.conexion.setCatalog(database);
			System.out.println(
					"Conexion establecida correctamente");
					
		} catch (ClassNotFoundException e) {
			System.out.println("error registrando el driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No se ha podido conectar");
			e.printStackTrace();
		}
		return this.conexion;
	}
	
	public void desconectar() {
		try {
			this.conexion.close();
			System.out.println("Conexión liberada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al desconectar de la BBDD.");
			e.printStackTrace();
		}
	}
	
}
