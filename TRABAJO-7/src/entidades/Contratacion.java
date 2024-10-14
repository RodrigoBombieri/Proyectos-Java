package entidades;

public class Contratacion {
	private int idContratacion;
	private int idSeguro;
	private String nombreUsuario;
	private float costoContratacion;
	
	public Contratacion() {
		
	}
	public Contratacion(int idContratacion, int idSeguro, String nombreUsuario, float costoContratacion) {
		super();
		this.idContratacion = idContratacion;
		this.idSeguro = idSeguro;
		this.nombreUsuario = nombreUsuario;
		this.costoContratacion = costoContratacion;
	}
	public int getIdContratacion() {
		return idContratacion;
	}
	public void setIdContratacion(int idContratacion) {
		this.idContratacion = idContratacion;
	}
	public int getIdSeguro() {
		return idSeguro;
	}
	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public float getCostoContratacion() {
		return costoContratacion;
	}
	public void setCostoContratacion(float costoContratacion) {
		this.costoContratacion = costoContratacion;
	}
	@Override
	public String toString() {
		return "Contratacion [idContratacion=" + idContratacion + ", idSeguro=" + idSeguro + ", nombreUsuario="
				+ nombreUsuario + ", costoContratacion=" + costoContratacion + "]";
	}
	
	
}
