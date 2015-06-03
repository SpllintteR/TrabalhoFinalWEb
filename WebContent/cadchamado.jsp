<%@page import="java.util.Date"%>
<%@page import="com.dao.ChamadoDAO"%>
<%@page import="com.modelodados.Chamado"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Chamados</title>
</head>
<body>
	<%
	String acao = request.getParameter("acao");
	Chamado chamado = new Chamado();
	if ("A".equals(acao)) {
		int codigo = Integer.parseInt(request.getParameter("edId"));
		ChamadoDAO dao = new ChamadoDAO();
		chamado = dao.obter(codigo);
	} else {
		acao = "I";
		chamado.setTitulo("");
		chamado.setDescricao("");
		chamado.setDataCriacao(new Date());
	}
%>

	<form action="Chamado">
		Id: <input type="text" name="edId" value="<%= chamado.getId() %>" /><br />

		Título: <input type="text" name="edTitulo"
			value="<%= chamado.getTitulo() %>" /><br /> Descrição: <input
			type="text" name="edDescricao" value="<%= chamado.getDescricao() %>" /><br />

		Data de Criação: <input type="text" name="edDataCriacao"
			value="<%= chamado.getDataCriacao().toLocaleString() %>" /><br /> <input
			type="submit" value="Gravar" /><br /> <input type="hidden"
			name="acao" value="<%= acao %>" />
	</form>

</body>
</html>