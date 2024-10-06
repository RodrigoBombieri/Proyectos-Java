package daoImpl;

import java.lang.reflect.GenericArrayType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PersonaDao;
import entidad.Persona;

public class PersonaDaoImpl implements PersonaDao {
	
	private static final String insert = "INSERT INTO personas2(dni, nombre, apellido) VALUES(?,?,?)";
	private static final String delete = "DELETE FROM personas2 WHERE dni = ?";
	private static final String readall = "SELECT * FROM personas2";
	private static final String edit = "UPDATE personas2 SET nombre = ?, apellido = ? WHERE dni = ?";


	@Override
	public boolean insert(Persona persona) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement ps;
		boolean isInsertExitoso = false;
		try {
			ps = conexion.prepareStatement(insert);
			ps.setString(1, persona.getDni());
			ps.setString(2, persona.getNombre());
			ps.setString(3, persona.getApellido());
			if(ps.executeUpdate()>0) {
				conexion.commit();
				isInsertExitoso = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return isInsertExitoso;
	}

	@Override
	public boolean delete(Persona persona_a_eliminar) {
		Connection conexion = Conexion.getConexion().getSQLConexion();
		PreparedStatement ps;
		boolean isDeleteExitoso = false;
		try {
			ps = conexion.prepareStatement(delete);
			ps.setString(1, persona_a_eliminar.getDni());
			if(ps.executeUpdate()>0) {
				conexion.commit();
				isDeleteExitoso = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	
		}
			
		return isDeleteExitoso;
	}

	@Override
	public List<Persona> readAll() {
		PreparedStatement ps;
		ResultSet rs;
		ArrayList<Persona> personas = new ArrayList<Persona>();
		Conexion conexion = Conexion.getConexion();
		try {
			ps = conexion.getSQLConexion().prepareStatement(readall);
			rs = ps.executeQuery();
			while(rs.next()) {
				personas.add(getPersona(rs));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}
	
	public boolean edit(Persona persona_modificar) {
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    PreparedStatement ps;
	    boolean isEditExitoso = false;
	    try {
	        ps = conexion.prepareStatement(edit);
	        ps.setString(1, persona_modificar.getNombre());
	        ps.setString(2, persona_modificar.getApellido());
	        ps.setString(3, persona_modificar.getDni());
	        
	        if (ps.executeUpdate() > 0) {
	            isEditExitoso = true; // Se ha actualizado al menos una fila
	        }
	        
	        conexion.commit();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            conexion.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    return isEditExitoso;
	}

	
	private Persona getPersona(ResultSet rs) throws SQLException{
		String dni = rs.getString(1);
		String nombre = rs.getString(2);
		String apellido = rs.getString(3);
		return new Persona(dni, nombre, apellido);
	}

}
