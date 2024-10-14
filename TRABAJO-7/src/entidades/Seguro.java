package entidades;

public class Seguro {
	private int idSeguro;
	private String descripcion;
	private TipoSeguro tipoSeguro;
	private float costoContratacion;
	private float costoAsegurado;
	
	private static int idCont=1;
	
	public Seguro() {
		this.idSeguro = idCont++;
	}
	
	public Seguro(int idSeguro, String descripcion, TipoSeguro tipoSeguro, float costoContratacion,
			float costoAsegurado) {
		super();
		this.idSeguro = idSeguro;
		this.descripcion = descripcion;
		this.tipoSeguro = tipoSeguro;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}
	
	public static int getNextId() {
		return idCont;
	}

	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}

	public float getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(float costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public float getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(float costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", tipoSeguro=" + tipoSeguro
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	
	
	
}
