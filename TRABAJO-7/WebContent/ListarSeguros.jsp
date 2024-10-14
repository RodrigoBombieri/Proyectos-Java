<%@ page import="entidades.Seguro" %>
<%@page import="java.util.ArrayList"%>
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
	<h1><b>Tipo de seguros de la base de datos</b></h1>
	
	<form action="servletSeguros" method="get">
    Búsqueda por tipo de seguros: 
    <select name="tipoSeguro" onchange="document.getElementById('tipoDescripcion').value = this.options[this.selectedIndex].text;">
        <option value="1">Seguro de casas</option>
        <option value="2">Seguro de vida</option>
        <option value="3">Seguro de motos</option>
    </select>
    <input type="hidden" name="tipoDescripcion" id="tipoDescripcion" value="Seguro de casas">
    <input type="submit" name="btnFiltrar" value="Filtrar">
</form>
	
<% 
	ArrayList<Seguro> listaSeguros = null;
	if(request.getAttribute("listaS")!=null)
	{
		listaSeguros = (ArrayList<Seguro>) request.getAttribute("listaS");
	}

 %>


<table id="table_id" class="display">
    <thead>
        <tr>
            <th>ID Seguro</th>
            <th>Descripcion seguro</th>
            <th>Descripcion Tipo seguro</th>
            <th>Costo Contratación</th>
            <th>Costo máximo asegurado</th>
        </tr>
    </thead>
    <tbody>
       <%  if(listaSeguros!=null)
		for(Seguro seguro : listaSeguros) 
		{
	%>
		<tr>  
		    <form name="formulario" action="servletSeguros?idSeguro=<%=seguro.getIdSeguro()%>" method="get">
				<td><%=seguro.getIdSeguro() %> </td> 
				<td><%=seguro.getDescripcion() %></td>   
				<td><%=seguro.getTipoSeguro().getDescripcion() %></td>
				<td> <%=seguro.getCostoContratacion() %></td>
				<td> <%=seguro.getCostoAsegurado() %></td>    
			</form> 
		</tr>
	<%  } %>
    </tbody>
</table>
	
</body>
</html>