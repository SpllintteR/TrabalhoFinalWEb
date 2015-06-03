<%@page import="java.util.ArrayList"%>
<%@page import="com.modelodados.Chamado"%>
<%@page import="com.dao.ChamadoDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chamado - Página inicial</title>
</head>
<body>
	<%
		ChamadoDAO dao = new ChamadoDAO();
		ArrayList<Chamado> lista = dao.listar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	%>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>Titulo</th>
			<th>Descricao</th>
			<th>DataCriacao</th>
			<th>Ações</th>
		</tr>
		<%
			for (int i = 0; i < lista.size(); i++) {
				Chamado chamado = (Chamado)lista.get(i);
		%>
		<tr>
			<td><%= chamado.getId() %></td>
			<td><%= chamado.getTitulo() %></td>
			<td><%= chamado.getDescricao() %></td>
			<td><%= sdf.format(chamado.getDataCriacao()) %></td>
			<td><a
				href="cadchamado.jsp?acao=A&edId=<%= chamado.getId()%>">Alterar</a>
				<a href="Chamado?acao=D&edId=<%= chamado.getId()%>">Excluir</a></td>
		</tr>
		<%
	}
%>
	</table>
	<a href="cadchamado.jsp">Inserir chamado</a>
</body>
</html>