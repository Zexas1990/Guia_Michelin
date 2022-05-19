package michelin.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import michelin.db.AccesoBBDD;
import michelin.model.Restaurante;

public class RestaurantesPersistencia {
	
	private AccesoBBDD acceso;
	
	public RestaurantesPersistencia() {
		acceso = new AccesoBBDD();
	}

	public String consultarIdPornombre(String nombre) {
		
		
		String id = null;
		
		// SELECT ID FROM RESTAURANTES WHERE NOMBRE = ?
		String query = "SELECT " + RestaurantesContract.COLUMN_ID + " FROM " + RestaurantesContract.NOMBRE_TABLA +
						" WHERE " + RestaurantesContract.COLUMN_NOMBRE + " = ?";
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rlst = null;
		
		
		try {
			con = acceso.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, nombre);
			
			rlst = pstmt.executeQuery();
			
			if(rlst.next()) {
				id = rlst.getString(RestaurantesContract.COLUMN_ID);
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				//Los recursos siempre se cierran en orden inverso al que se abrieron
				
				if(rlst != null) rlst.close();
				if(pstmt != null) pstmt.close();
				if(rlst != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		
		
		return id;
	}

	public int registrarRestaurante(Restaurante restaurante) {
		int resultado = 0;
		
		String query = "INSERT INTO " + RestaurantesContract.NOMBRE_TABLA + " ( "+ RestaurantesContract.COLUMN_NOMBRE
						+ ", " + RestaurantesContract.COLUMN_REGION + ", " + RestaurantesContract.COLUMN_CIUDAD + ", " + RestaurantesContract.COLUMN_DISTIN 
						+ ", " + RestaurantesContract.COLUMN_DIREC + ", " + RestaurantesContract.COLUMN_PREC_MIN + ", " + RestaurantesContract.COLUMN_PREC_MAX 
						+ ", " + RestaurantesContract.COLUMN_COCINA + ", " + RestaurantesContract.COLUMN_TELEF + ", " + RestaurantesContract.COLUMN_WEB 
						+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = acceso.getConexion();
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, restaurante.getNombre());
			pstmt.setString(2, restaurante.getRegion());
			pstmt.setString(3, restaurante.getCiudad());
			pstmt.setInt(4, restaurante.getDistincion());
			pstmt.setString(5, restaurante.getDireccion());
			pstmt.setDouble(6, restaurante.getPrecio_min());
			pstmt.setDouble(7, restaurante.getPrecio_max());
			pstmt.setString(8, restaurante.getCocina());
			pstmt.setString(9, restaurante.getTelefono());
			pstmt.setString(10, restaurante.getWeb());
			
			resultado = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return resultado;
	}

	
	
	
	
	
	
	
	
	
	
}
