package michelin.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dam.view.VConsulta;
import michelin.control.MichelinControl;
import michelin.db.AccesoBBDD;
import michelin.model.Restaurante;

public class RestaurantesPersistencia {
	
	private AccesoBBDD acceso;
	
	public RestaurantesPersistencia() {
		acceso = new AccesoBBDD();
	}
	
	
	public ArrayList<Restaurante> verRest() {
		ArrayList<Restaurante> listRest = new ArrayList<Restaurante>();
		
		String query  = "SELECT * " +  " FROM " + RestaurantesContract.NOMBRE_TABLA;
		
		Connection con = null;
		Statement stml = null;
		ResultSet rslt = null;
		
		try {
			con = acceso.getConexion();
			
			stml = con.createStatement();
			
			rslt = stml.executeQuery(query);
			
		Restaurante rest;
	
		String nombre;
		String region;
		String ciudad;
		int disti;
		String direc;
		String cocina;
		double pMax;
		double pMin;
		String telefono;
		String web;
		
		while (rslt.next()) {
			
			
			nombre = rslt.getString(2);
			region = rslt.getString(3);
			ciudad = rslt.getString(4);
			disti = rslt.getInt(5);
			direc = rslt.getString(6);
			pMin = rslt.getDouble(7);
			pMax = rslt.getDouble(8);
			cocina = rslt.getString(9);
			telefono = rslt.getString(10);
			web = rslt.getString(11);
			
			rest = new Restaurante(nombre, region, ciudad, disti, direc, pMin, pMax, cocina, telefono, web);
			
			listRest.add(rest);
		}
					
					
		} catch (ClassNotFoundException e) {
			System.out.println("El driver indicado no es correcto");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error en la base de datos");
			e.printStackTrace();
		}finally {
			try {
				if (rslt != null) rslt.close(); 
				if (stml != null) stml.close();	
				if (con != null) con.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return listRest;
		
	}
	
	public ArrayList<Restaurante> filterTable(int distin, String reg) {
		ArrayList<Restaurante> listFilter = new ArrayList<Restaurante>();
		 
		// distin = 0 y reg = "TODAS"
		String query = "SELECT * " + " FROM " + RestaurantesContract.NOMBRE_TABLA;
		
		if (distin != 0 && reg.equals(VConsulta.REGIONES)) {
			query += " WHERE " + RestaurantesContract.COLUMN_DISTIN + " = ?" ;
			
		} else if (distin == 0 && !reg.equals(VConsulta.REGIONES)) {
			query += " WHERE " + RestaurantesContract.COLUMN_REGION + " = ?" ;
			
		} else if (distin != 0 && !reg.equals(VConsulta.REGIONES)) {
			query += " WHERE " + RestaurantesContract.COLUMN_DISTIN + " = ?" 
					+ " and " + RestaurantesContract.COLUMN_REGION + " = ?" ;
		}
						
		
		Connection con = null;
		PreparedStatement pstml = null;
		ResultSet rstl = null;
		
		
		try {
			con = acceso.getConexion();
			pstml = con.prepareStatement(query);
			
			if (distin != 0 && reg.equals(VConsulta.REGIONES)) {
				pstml.setInt(1, distin);
				
			} else if (distin == 0 && !reg.equals(VConsulta.REGIONES)) {
				pstml.setString(1, reg);
				
			} else if (distin != 0 && !reg.equals(VConsulta.REGIONES)) {
				pstml.setInt(1, distin);
				pstml.setString(2, reg);
			}
			
		
			rstl = pstml.executeQuery();
			
			Restaurante rest;
			
			String nombre;
			String region;
			String ciudad;
			int disti;
			String direc;
			String cocina;
			double pMax;
			double pMin;
			String telefono;
			String web;
			
			while (rstl.next()) {
				
				
				nombre = rstl.getString(2);
				region = rstl.getString(3);
				ciudad = rstl.getString(4);
				disti = rstl.getInt(5);
				direc = rstl.getString(6);
				pMin = rstl.getDouble(7);
				pMax = rstl.getDouble(8);
				cocina = rstl.getString(9);
				telefono = rstl.getString(10);
				web = rstl.getString(11);
				
				rest = new Restaurante(nombre, region, ciudad, disti, direc, pMin, pMax, cocina, telefono, web);
				
				listFilter.add(rest);
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				if (rstl != null) rstl.close(); 
				if (pstml != null) pstml.close();	
				if (con != null) con.close();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return listFilter;
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


	public ArrayList<String> requestRegiones() {
		ArrayList<String> listRegiones = new ArrayList<String>();
		
		String query = "SELECT DISTINCT " + RestaurantesContract.COLUMN_REGION + " FROM " + RestaurantesContract.NOMBRE_TABLA;
		
		Connection con = null;
		Statement stml = null;
		ResultSet rstl = null;
		
		try {
			
			con = acceso.getConexion();
			
			stml = con.createStatement();
			
			rstl = stml.executeQuery(query);
			
			
			String regiones;
			
			while (rstl.next()) {
				
				regiones = rstl.getString(RestaurantesContract.COLUMN_REGION);
				
				listRegiones.add(regiones);
			
			}
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			 
				try {
					if (rstl != null)rstl.close();
					if (stml != null) stml.close();	
					if (con != null) con.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				} {
				
			}
		}
		
		
		
		return listRegiones;
	}

	
	
	
	
	
	
	
	
	
	
}
