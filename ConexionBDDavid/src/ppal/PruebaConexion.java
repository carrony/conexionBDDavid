package ppal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.ConexionBD;

public class PruebaConexion {

	public static void main(String[] args) {
		System.out.println("Los datos de los fabricante son: ");
		mostrarFabricantes();
		

	}
	
	// consulta select sobre la tabla de fabricantes.
	public static void mostrarFabricantes() {
		ConexionBD conexionBD= new ConexionBD();
		
		System.out.println("Conectando a la base de datos...");
		Connection conex = conexionBD.getConexion();
		
		Statement stmt=null;
		ResultSet rs=null;
		//.... ejecuto las sentenicas contra la bd
		try {
			stmt = conex.createStatement();
			String sql = "select * from fabricante";
			rs = stmt.executeQuery(sql);
			// iterar sobre los resultados de la consulta
			while (rs.next()) {
				int codigo = rs.getInt("codigo");
				String nombre = rs.getString("nombre");
				
				System.out.println("Código: "+codigo+ 
						"\t Fabricante:"+nombre);
			}
					
			
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			
		}
		
		conexionBD.desconectar();
	}

}
