package ppal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utilidades.ConexionBD;

public class PruebaConexion {

	public static void main(String[] args) {
		System.out.println("Los datos de los fabricante son: ");
		mostrarFabricantes();
		
		System.out.println("Insertando un nuevo fabricante....");
		System.out.println("Código: 13");
		System.out.println("Nombre: Realtek");
		int num = insertarFabricante(13,"Realtek");
		System.out.println("Se ha insertado "+num+ "fila");
		
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

	public static int insertarFabricante(int codigo, 
			String fabricante) {
		ConexionBD conexionBD= new ConexionBD();
		int numFilas=0;
		System.out.println("Conectando a la base de datos...");
		Connection conex = conexionBD.getConexion();
		
//		Statement stmt = conex.createStatement();
//		stmt.executeUpdate(
//				"insert into fabricante(codigo,nombre) "
//				+ "values("+codigo+",'"+fabricante+"')");

		PreparedStatement pstmt=null;
		try {
			pstmt = conex.prepareStatement(
					"insert into fabricante(codigo,nombre) "
					+ "values(?,?)"
					);
			
			pstmt.setInt(1, codigo);
			pstmt.setString(2, fabricante);
			
			// ejecutar la inserción
			numFilas=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conexionBD.desconectar();
			} catch (SQLException e) {
				// TODO Bloque catch generado automáticamente
				e.printStackTrace();
			}
			
		}
		return numFilas;
		
	}
	
}
