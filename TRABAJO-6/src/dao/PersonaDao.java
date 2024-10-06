package dao;

import java.util.List;

import entidad.Persona;

public interface PersonaDao {
	public boolean insert(Persona persona);
	public boolean delete(Persona persona_a_eliminar);
	public boolean edit(Persona persona_modificar);
	public List<Persona> readAll();

}
