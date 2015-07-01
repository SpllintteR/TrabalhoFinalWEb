<%@page import="java.util.Date"%>
<%@page import="com.dao.ChamadoDAO"%>
<%@page import="com.modelodados.Chamado"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload de Anexo</title>
    </head>
 
    <body>
	    <%
		Chamado chamado = new Chamado();
		int codigo = Integer.parseInt(request.getParameter("edId"));
		ChamadoDAO dao = new ChamadoDAO();
		chamado = dao.obter(codigo);
		%> 
        <div>
            <h3> Arquivo: </h3>
            <form action="Upload" method="post" enctype="multipart/form-data">
                <input type="file" name="file" />
                <input type="submit" value="upload" />
                <input type="hidden" name="chamadoId" value="<%= chamado.getId()%>" />
            </form>          
        </div>
      
    </body>
</html>