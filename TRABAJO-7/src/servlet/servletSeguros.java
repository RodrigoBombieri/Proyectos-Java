package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.SeguroDao;
import entidades.Seguro;
import entidades.TipoSeguro;
import negocio.SeguroNegocio;
import negocioImpl.SeguroNegocioImpl;

/**
 * Servlet implementation class servletSeguros
 */
@WebServlet("/servletSeguros")
public class servletSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public servletSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if (request.getParameter("btnFiltrar") != null) {
	        int tipoSeguroId = Integer.parseInt(request.getParameter("tipoSeguro")); // ID del tipo de seguro seleccionado

	        SeguroNegocioImpl sNeg = new SeguroNegocioImpl();
	        ArrayList<Seguro> listaFiltrada = sNeg.filtrarPorTipoSeguro(tipoSeguroId); // Llamada al método de filtrado en la capa de negocio

	        request.setAttribute("listaS", listaFiltrada);
	        RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
	        rd.forward(request, response);
	    }
		
		if(request.getParameter("Param")!=null) {
			//SeguroDao sdao = new SeguroDao();
			SeguroNegocioImpl sNeg = new SeguroNegocioImpl();
			ArrayList<Seguro> listaSeguros = sNeg.readAll();
			//ArrayList<Seguro> listaSeguros = sdao.obtenerSeguros();
			
			request.setAttribute("listaS", listaSeguros);
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");
			rd.forward(request, response);
		}
		
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAceptar")!=null) {
			String error = validarFormulario(request);
			
			if (error != null) {
				request.setAttribute("error", error);
				RequestDispatcher rd = request.getRequestDispatcher("/FormularioSeguro.jsp");
				rd.forward(request, response);
				return;
			}
			
			Seguro s = new Seguro();
			s.setDescripcion(request.getParameter("txtDescripcion"));
			TipoSeguro tipoSeguro = new TipoSeguro();
			tipoSeguro.setIdTipo(Integer.parseInt(request.getParameter("tipoSeguro")));
			tipoSeguro.setDescripcion(request.getParameter("tipoDescripcion"));
		    s.setTipoSeguro(tipoSeguro);
			s.setCostoContratacion(Float.parseFloat(request.getParameter("txtCostoContratacion")));
			s.setCostoAsegurado(Float.parseFloat(request.getParameter("txtCostoMaxAsegurado")));
			
			SeguroNegocioImpl sNeg = new SeguroNegocioImpl();
			boolean estado = sNeg.insert(s);
			
			request.setAttribute("cantFilas", estado);
			RequestDispatcher rd = request.getRequestDispatcher("/Inicio.jsp");
			rd.forward(request, response);
		}
	}
	private String validarFormulario(HttpServletRequest request) {
		String descripcion = request.getParameter("txtDescripcion");
		String tipoSeguroId = request.getParameter("tipoSeguro");
		String tipoDescripcion = request.getParameter("tipoDescripcion");
		String costoContratacionStr = request.getParameter("txtCostoContratacion");
		String costoMaxAseguradoStr = request.getParameter("txtCostoMaxAsegurado");

		// Verifica que los campos no estén vacíos
		if (descripcion == null || descripcion.trim().isEmpty()) {
			return "La descripción es obligatoria.";
		}
		if (tipoSeguroId == null || tipoSeguroId.trim().isEmpty()) {
			return "Debes seleccionar un tipo de seguro.";
		}
		if (tipoDescripcion == null || tipoDescripcion.trim().isEmpty()) {
			return "La descripción del tipo de seguro es obligatoria.";
		}
		if (costoContratacionStr == null || costoContratacionStr.trim().isEmpty()) {
			return "El costo de contratación es obligatorio.";
		}
		if (costoMaxAseguradoStr == null || costoMaxAseguradoStr.trim().isEmpty()) {
			return "El costo máximo asegurado es obligatorio.";
		}

		// Verificar que los campos numéricos sean válidos
		try {
			Float.parseFloat(costoContratacionStr);
		} catch (NumberFormatException e) {
			return "El costo de contratación debe ser un número válido.";
		}
		try {
			Float.parseFloat(costoMaxAseguradoStr);
		} catch (NumberFormatException e) {
			return "El costo máximo asegurado debe ser un número válido.";
		}

		// Si todo está correcto, devolver null (sin errores)
		return null;
	}

}
