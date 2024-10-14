package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import dominio.SeguroDao;
import entidades.Seguro;
import negocio.SeguroNegocio;

public class SeguroNegocioImpl implements SeguroNegocio {
	SeguroDao sdao = new SeguroDao();
	private ArrayList<Seguro> seguros;
	
	public SeguroNegocioImpl() {
		
	}
	@Override
	public boolean insert(Seguro seguro) {
		boolean estado = false;
		if(seguro.getDescripcion().trim().length()>0 && seguro.getTipoSeguro().getDescripcion().trim().length()>0 && 
				seguro.getCostoContratacion()>0 && seguro.getCostoAsegurado()>0) {
			estado = sdao.agregarSeguro(seguro);
		}
		return estado;
	}

	@Override
	public ArrayList<Seguro> readAll() {
		return sdao.obtenerSeguros();
	}

	@Override
	public ArrayList<Seguro> filtrarPorTipoSeguro(int tipoSeguroId) {
	    return sdao.obtenerSegurosPorTipo(tipoSeguroId);
	}

}
