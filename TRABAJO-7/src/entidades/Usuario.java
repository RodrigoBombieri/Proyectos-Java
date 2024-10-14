package entidades;

public class Usuario {
	private String dni;
	private String nombre;
	private String apellido;
	private String nombreUsuario;
	private String pass;
	private TipoSeguro tipoSeguro;
	
	public Usuario() {
		
	}
	
	public Usuario(String dni, String nombre, String apellido, String nombreUsuario, String pass,
			TipoSeguro tipoSeguro) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nombreUsuario = nombreUsuario;
		this.pass = pass;
		this.tipoSeguro = tipoSeguro;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", nombreUsuario="
				+ nombreUsuario + ", pass=" + pass + ", tipoSeguro=" + tipoSeguro + "]";
	}
	
	

}
