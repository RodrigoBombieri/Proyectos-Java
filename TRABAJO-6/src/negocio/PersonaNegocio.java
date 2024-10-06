package negocio;

import java.util.List;

import entidad.Persona;

public interface PersonaNegocio {
	public boolean insert(Persona persona);
	public boolean delete(Persona persona_a_eliminar);
	public List<Persona> readAll();
	public boolean edit(Persona persona_modificar);
	public boolean esDniUnico(String dni);
}
