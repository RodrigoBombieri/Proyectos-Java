<%@page import="entidades.Seguro" %>
<%@page import="dominio.SeguroDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="Inicio.jsp">Inicio</a>
	<a href="AgregarSeguro.jsp">Agregar Seguro</a>
	<a href="servletSeguros?Param=1">Listar Seguros</a>
	<h1><b>Agregar Seguros</b></h1>
	<form action="servletSeguros" method="post">
		<table border="0" cellpadding="5">
			<tr>
				<td><label>Id Seguro</label></td>
					<% 
    				SeguroDao seguroDao = new SeguroDao();
    				int nextId = seguroDao.getLastId() + 1; 
					%>
				<td><%= nextId %></td>
			</tr>
			<tr>
				<td><label>Descripción</label></td>
				<td><input type="text" name="txtDescripcion"></td>
			</tr>
			<tr>
				<td><label>Tipo de seguro</label></td>
				<td>
					<select name="tipoSeguro" onchange="document.getElementById('tipoDescripcion').value = this.options[this.selectedIndex].text;">
						<option value="1">Seguro de casas</option>
						<option value="2">Seguro de vida</option>
						<option value="3">Seguro de motos</option>
					</select>
					<input type="hidden" name="tipoDescripcion" id="tipoDescripcion" value="Seguro de casas">
				</td>
			</tr>
			<tr>
				<td><label>Costo contratación</label></td>
				<td><input type="text" name="txtCostoContratacion"></td>
			</tr>
			<tr>
				<td><label>Costo máximo asegurado</label></td>
				<td><input type="text" name="txtCostoMaxAsegurado"></td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2"><input type="submit" name="btnAceptar" value="Aceptar"></td>
			</tr>
		</table>
	</form>
</body>
</html>