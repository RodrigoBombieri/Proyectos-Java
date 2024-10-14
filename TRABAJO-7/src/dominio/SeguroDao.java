package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Seguro;
import entidades.TipoSeguro;

public class SeguroDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "segurosgroup";
	
	public boolean agregarSeguro(Seguro seguro) {
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return false;
	    }
	    
	    Connection cn = null;
	    try {
	        cn = DriverManager.getConnection(host + dbName, user, pass);
	        Statement st = cn.createStatement();
	        String query = "Insert into seguros(descripcion,idTipo,costoContratacion,costoAsegurado) values ('" 
	            + seguro.getDescripcion() + "','" 
	            + seguro.getTipoSeguro().getIdTipo() + "','" 
	            + seguro.getCostoContratacion() + "','" 
	            + seguro.getCostoAsegurado() + "')";
	        
	        int filas = st.executeUpdate(query);
	        
	        // Si filas es mayor a 0, la inserción fue exitosa
	        return filas > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; // Si ocurre una excepción, retornamos false
	    } finally {
	        try {
	            if (cn != null) {
	                cn.close(); // Aseguramos cerrar la conexión
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	public ArrayList<Seguro> obtenerSeguros() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Seguro> lista = new ArrayList<Seguro>();
		Connection con = null;
		try{
			con = DriverManager.getConnection(host + dbName, user, pass);
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT s.idSeguro, s.descripcion, s.idTipo, s.costoContratacion, s.costoAsegurado, ts.descripcion AS tipoDescripcion\r\n" + 
					"FROM seguros s\r\n" + 
					"JOIN tipoSeguros ts ON s.idTipo = ts.idTipo;");
			
			while(rs.next()){
				
				Seguro seguroRs = new Seguro();
				seguroRs.setIdSeguro(rs.getInt("idSeguro"));
				seguroRs.setDescripcion(rs.getString("descripcion"));
				TipoSeguro tipoSeguro = new TipoSeguro();
			    tipoSeguro.setIdTipo(rs.getInt("idTipo"));
			    tipoSeguro.setDescripcion(rs.getString("tipoDescripcion"));
			    seguroRs.setTipoSeguro(tipoSeguro);
				seguroRs.setCostoContratacion(rs.getFloat("costoContratacion"));
				seguroRs.setCostoAsegurado(rs.getFloat("costoAsegurado"));
				
				lista.add(seguroRs);
			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
		}
		
		return lista;
	}
	
	public ArrayList<Seguro> obtenerSegurosPorTipo(int tipoSeguroId) {
	    ArrayList<Seguro> lista = new ArrayList<Seguro>();
	    Connection con = null;

	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection(host + dbName, user, pass);
	        Statement st = con.createStatement();
	        
	        String query = "SELECT s.idSeguro, s.descripcion, s.idTipo, s.costoContratacion, s.costoAsegurado, ts.descripcion AS tipoDescripcion " +
	                       "FROM seguros s " +
	                       "JOIN tipoSeguros ts ON s.idTipo = ts.idTipo " +
	                       "WHERE s.idTipo = " + tipoSeguroId;

	        ResultSet rs = st.executeQuery(query);

	        while (rs.next()) {
	            Seguro seguroRs = new Seguro();
	            seguroRs.setIdSeguro(rs.getInt("idSeguro"));
	            seguroRs.setDescripcion(rs.getString("descripcion"));
	            TipoSeguro tipoSeguro = new TipoSeguro();
	            tipoSeguro.setIdTipo(rs.getInt("idTipo"));
	            tipoSeguro.setDescripcion(rs.getString("tipoDescripcion"));
	            seguroRs.setTipoSeguro(tipoSeguro);
	            seguroRs.setCostoContratacion(rs.getFloat("costoContratacion"));
	            seguroRs.setCostoAsegurado(rs.getFloat("costoAsegurado"));

	            lista.add(seguroRs);
	        }
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return lista;
	}
	
	public int getLastId() {
	    int lastId = 0;  // Si no hay seguros, el primer ID será 1
	    String sql = "SELECT MAX(idSeguro) FROM seguros";
	    Connection connection = null;

	    try {
	        // Cargar el driver de MySQL
	        Class.forName("com.mysql.jdbc.Driver");
	        
	        // Establecer la conexión a la base de datos
	        connection = DriverManager.getConnection(host + dbName, user, pass);
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        
	        // Si hay resultados, obtener el último ID
	        if (rs.next()) {
	            lastId = rs.getInt(1);  // Obtener el último ID
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (connection != null) {
	                connection.close();  // Cerrar la conexión
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return lastId;
	}

}
