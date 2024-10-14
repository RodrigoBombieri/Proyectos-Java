package negocio;

import java.util.ArrayList;
import java.util.List;

import entidades.Seguro;

public interface SeguroNegocio {
	public boolean insert(Seguro seguro);
	public ArrayList<Seguro> readAll();
	public List<Seguro> filtrarPorTipoSeguro(int tipoSeguroId);

}
